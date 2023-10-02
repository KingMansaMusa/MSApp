package com.example.msapp.feature_subscriber.domain.use_case

import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.repository.SubscriberRepository
import com.example.msapp.feature_subscriber.domain.util.OrderType
import com.example.msapp.feature_subscriber.domain.util.SubscriberOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
class GetSubscribersUseCase(private val repository: SubscriberRepository) {

    operator fun invoke(
        subscriberOrder: SubscriberOrder = SubscriberOrder.Date(OrderType.Descending)
    ): Flow<List<Subscriber>> {
        return repository.getSubscribers().map { subscribers ->
            when(subscriberOrder.orderType){
                is OrderType.Ascending -> {
                    when(subscriberOrder){
                        is SubscriberOrder.Name -> subscribers.sortedBy { it.name.lowercase() }
                        is SubscriberOrder.Date -> subscribers.sortedBy { it.startDate }
                        is SubscriberOrder.Package ->subscribers.sortedBy { it.serviceType }
                    }
                }
                is OrderType.Descending -> {
                    when(subscriberOrder){
                        is SubscriberOrder.Name -> subscribers.sortedByDescending { it.name.lowercase() }
                        is SubscriberOrder.Date -> subscribers.sortedByDescending { it.startDate }
                        is SubscriberOrder.Package ->subscribers.sortedByDescending { it.serviceType }
                    }
                }
            }
        }
    }

}