package com.example.msapp.feature_subscriber.domain.util

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
sealed class SubscriberOrder(val orderType: OrderType) {
    class Name(orderType: OrderType) : SubscriberOrder(orderType)
    class Date(orderType: OrderType) : SubscriberOrder(orderType)
    class Package(orderType: OrderType) : SubscriberOrder(orderType)

    fun copy(orderType: OrderType): SubscriberOrder {
        return when (this) {
            is Name -> Name(orderType)
            is Date -> Date(orderType)
            is Package -> Package(orderType)
        }
    }
}
