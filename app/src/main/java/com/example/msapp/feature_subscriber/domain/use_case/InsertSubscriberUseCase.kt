package com.example.msapp.feature_subscriber.domain.use_case

import com.example.msapp.feature_subscriber.domain.model.InvalidSubscriberException
import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.repository.SubscriberRepository

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
class InsertSubscriberUseCase(private val repository: SubscriberRepository) {

    @Throws(InvalidSubscriberException::class)
    suspend operator fun invoke(subscriber: Subscriber) {

        if (subscriber.name.isBlank()) {
            throw InvalidSubscriberException("SubscriberName cannot be empty!")
        }

        if (subscriber.phoneNumber.isBlank()) {
            throw InvalidSubscriberException("Subscriber Phone Number cannot be empty")
        }

        if (subscriber.email.isBlank()) {
            throw InvalidSubscriberException("Subscriber Email cannot be empty")
        }

        if (subscriber.location.isBlank()) {
            throw InvalidSubscriberException("Subscriber Location cannot be blank")
        }

        if (subscriber.serviceType.isBlank()) {
            throw InvalidSubscriberException("Subscriber Package cannot be empty")
        }

        if (subscriber.toString().isBlank()) {
            throw InvalidSubscriberException("Subscriber Subscription Start Date cannot be empty")
        }
        repository.insertSubscriber(subscriber)
    }
}