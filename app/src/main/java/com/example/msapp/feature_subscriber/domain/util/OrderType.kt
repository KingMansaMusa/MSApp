package com.example.msapp.feature_subscriber.domain.util

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
sealed class OrderType{
    object Ascending: OrderType()
    object Descending: OrderType()
}
