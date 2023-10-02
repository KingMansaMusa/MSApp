package com.example.msapp.feature_subscriber.presentation.add_subscriber

import android.widget.DatePicker
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msapp.feature_subscriber.domain.model.InvalidSubscriberException
import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.use_case.SubscriberUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import java.util.Date
import java.util.UUID
import javax.inject.Inject

/**
 * Created by Stephen Obeng Takyi on 04/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */

@HiltViewModel
class AddSubscriberViewModel @Inject constructor(
    private val subscriberUseCases: SubscriberUseCases,
    savedHostHandle: SavedStateHandle
) : ViewModel() {

    private val _subscriberName = mutableStateOf("")
    val subscriberName: State<String> = _subscriberName

    private val _subscriberEmail = mutableStateOf("")
    val subscriberEmail: State<String> = _subscriberEmail

    private val _subscriberDob = mutableStateOf("")
    val subscriberDob: State<String> = _subscriberDob

    private val _subscriberPhoneNumber = mutableStateOf("")
    val subscriberPhoneNumber: State<String> = _subscriberPhoneNumber

    private val _subscriberLocation = mutableStateOf("")
    val subscriberLocation: State<String> = _subscriberLocation

    private val _subscriberStartDate = mutableStateOf("")
    val subscriberStartDate: State<String> = _subscriberStartDate

    private val _subscriberServiceType = mutableStateOf("")
    val subscriberServiceType: State<String> = _subscriberServiceType

    val serviceDropdownExpanded = mutableStateOf(false)


//    private val _subscriberServiceType = mutableStateOf("")
//    val subscriberServiceType: State<String> = _subscriberServiceType

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow.asSharedFlow()

    private var currentSubscriberId: UUID? = null

    init {
        savedHostHandle.get<String>("subscriberId")?.let { subscriberId ->
            if (subscriberId != null) {
                viewModelScope.launch {
                    subscriberUseCases.getSubscriberByIdUseCase(UUID.fromString(subscriberId))
                        ?.also { subscriber ->
                            currentSubscriberId = subscriber.id

                            _subscriberName.value = subscriber.name

                            _subscriberEmail.value = subscriber.email

                            _subscriberDob.value = subscriber.dob

                            _subscriberPhoneNumber.value = subscriber.phoneNumber

                            _subscriberLocation.value = subscriber.location

                            _subscriberServiceType.value = subscriber.serviceType

                            _subscriberStartDate.value = subscriber.startDate

                        }
                }
            }
        }
    }

    fun onEvent(event: AddSubscriberEvent) {
        when (event) {
            is AddSubscriberEvent.EnteredName ->
                _subscriberName.value = event.value

            is AddSubscriberEvent.EnteredEmail ->
                _subscriberEmail.value = event.value

            is AddSubscriberEvent.EnteredDob ->
                _subscriberDob.value = event.value

            is AddSubscriberEvent.EnteredPhoneNumber ->
                _subscriberPhoneNumber.value = event.value

            is AddSubscriberEvent.EnteredLocation ->
                _subscriberLocation.value = event.value

            is AddSubscriberEvent.EnteredServiceType -> {
                _subscriberServiceType.value = event.value
                serviceDropdownExpanded.value = false
            }

            is AddSubscriberEvent.ToggleServiceDropdown ->
                serviceDropdownExpanded.value = !serviceDropdownExpanded.value

            is AddSubscriberEvent.EnteredStartDate ->
                _subscriberStartDate.value = event.value

            is AddSubscriberEvent.SaveSubscriber ->
                viewModelScope.launch {
                    try {
                        subscriberUseCases.insertSubscriberUseCase(
                            Subscriber(
                                id = currentSubscriberId ?: UUID.randomUUID(),
                                name = _subscriberName.value,
                                phoneNumber = _subscriberPhoneNumber.value,
                                email = _subscriberEmail.value,
                                dob = _subscriberDob.value,
                                location = _subscriberLocation.value,
                                serviceType = _subscriberServiceType.value,
                                startDate = _subscriberStartDate.value
                            )
                        )
                        _eventFlow.emit(UIEvent.SaveSubscriber)
                    } catch (e: InvalidSubscriberException) {
                        _eventFlow.emit(
                            UIEvent.ShowSnackBar(
                                message = e.message
                                    ?: "Something went wrong while saving subscriber"
                            )
                        )
                    }
                }
        }
    }

}