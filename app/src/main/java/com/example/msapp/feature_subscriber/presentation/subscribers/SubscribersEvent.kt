package com.example.msapp.feature_subscriber.presentation.subscribers

import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.util.SubscriberOrder

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
sealed class SubscribersEvent{
    data class Order(val subscriberOrder: SubscriberOrder): SubscribersEvent()
    data class DeleteSubscriber(val subscriber: Subscriber): SubscribersEvent()
    object RestoreSubscriber: SubscribersEvent()
    object ToggleOrderSection: SubscribersEvent()
}
