package com.example.msapp.feature_subscriber.domain.use_case

import com.example.msapp.feature_subscriber.data.data_source.SubscriberDao
import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.repository.SubscriberRepository

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
class DeleteSubscriberUseCase(private val repository: SubscriberRepository) {

    suspend operator fun invoke(subscriber: Subscriber) {
        repository.deleteSubscriber(subscriber)
    }

}