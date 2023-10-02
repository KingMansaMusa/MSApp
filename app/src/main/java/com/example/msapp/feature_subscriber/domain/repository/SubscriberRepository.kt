package com.example.msapp.feature_subscriber.domain.repository

import com.example.msapp.feature_subscriber.domain.model.Subscriber
import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
interface SubscriberRepository {

    fun getSubscribers(): Flow<List<Subscriber>>

    suspend fun getSubscriberById(id: UUID): Subscriber?

    suspend fun insertSubscriber(subscriber: Subscriber)

    suspend fun deleteSubscriber(subscriber: Subscriber)

}