package com.example.msapp.feature_subscriber.presentation.add_subscriber

import androidx.compose.ui.focus.FocusState

/**
 * Created by Stephen Obeng Takyi on 04/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
sealed class AddSubscriberEvent {
    data class EnteredName(val value: String) : AddSubscriberEvent()

    data class EnteredEmail(val value: String) : AddSubscriberEvent()

    data class EnteredDob(val value: String) : AddSubscriberEvent()

    data class EnteredPhoneNumber(val value: String) : AddSubscriberEvent()

    data class EnteredLocation(val value: String) : AddSubscriberEvent()

    data class EnteredServiceType(val value: String) : AddSubscriberEvent()

    data class EnteredStartDate(val value: String) : AddSubscriberEvent()

    object SaveSubscriber : AddSubscriberEvent()

    object ToggleServiceDropdown : AddSubscriberEvent()
}
