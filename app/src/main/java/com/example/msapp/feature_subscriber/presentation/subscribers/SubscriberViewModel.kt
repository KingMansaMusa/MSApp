package com.example.msapp.feature_subscriber.presentation.subscribers

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.use_case.SubscriberUseCases
import com.example.msapp.feature_subscriber.domain.util.OrderType
import com.example.msapp.feature_subscriber.domain.util.SubscriberOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */

@HiltViewModel
class SubscriberViewModel @Inject constructor(
    private val subscriberUseCases: SubscriberUseCases
) : ViewModel() {

    private val _state = mutableStateOf(SubscribersState())
    val state: State<SubscribersState> = _state

    private var recentlyDeletedSubscriber: Subscriber? = null
    private var getSubscribersJob: Job? = null

    init {
        getSubscribers(SubscriberOrder.Date(OrderType.Descending))
    }

    fun onEvent(event: SubscribersEvent) {
        when (event) {
            is SubscribersEvent.Order -> {
                if (state.value.subscriberOrder::class == event.subscriberOrder::class &&
                    state.value.subscriberOrder.orderType == event.subscriberOrder.orderType
                ) {
                    return
                }

                getSubscribers(event.subscriberOrder)
            }

            is SubscribersEvent.DeleteSubscriber -> {
                viewModelScope.launch {
                    subscriberUseCases.deleteSubscriberUseCase(event.subscriber)
                    recentlyDeletedSubscriber = event.subscriber
                }
            }

            is SubscribersEvent.RestoreSubscriber -> {
                viewModelScope.launch {
                    subscriberUseCases.insertSubscriberUseCase(
                        recentlyDeletedSubscriber ?: return@launch
                    )
                    recentlyDeletedSubscriber = null
                }
            }

            is SubscribersEvent.ToggleOrderSection -> {
                _state.value = state.value.copy(
                    isOderSectionVisible = !state.value.isOderSectionVisible
                )
            }
        }
    }

    private fun getSubscribers(subscriberOrder: SubscriberOrder) {
        getSubscribersJob?.cancel()
        getSubscribersJob =
            subscriberUseCases.getSubscribersUseCase(subscriberOrder).onEach { subscribers ->
                _state.value = state.value.copy(
                    subscribers = subscribers, subscriberOrder = subscriberOrder
                )
            }.launchIn(viewModelScope)
    }

}