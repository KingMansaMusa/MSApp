package com.example.msapp.feature_subscriber.presentation.subscribers

import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.util.OrderType
import com.example.msapp.feature_subscriber.domain.util.SubscriberOrder

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
data class SubscribersState(
    val subscribers: List<Subscriber> = emptyList(),
    val subscriberOrder: SubscriberOrder = SubscriberOrder.Date(OrderType.Descending),
    val isOderSectionVisible: Boolean = false
)
