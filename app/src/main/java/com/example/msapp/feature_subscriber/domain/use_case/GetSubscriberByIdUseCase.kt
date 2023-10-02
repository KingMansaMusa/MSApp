package com.example.msapp.feature_subscriber.domain.use_case

import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.repository.SubscriberRepository
import java.util.UUID

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
class GetSubscriberByIdUseCase(private val repository: SubscriberRepository) {

    suspend operator fun invoke(id: UUID): Subscriber? {
        return repository.getSubscriberById(id)
    }

}