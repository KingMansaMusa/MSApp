package com.example.msapp.feature_subscriber.data.repository

import com.example.msapp.feature_subscriber.data.data_source.SubscriberDao
import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.repository.SubscriberRepository
import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
class SubscriberRepositoryImpl(private val dao: SubscriberDao) : SubscriberRepository {

    override fun getSubscribers(): Flow<List<Subscriber>> {
        return dao.getSubscribers()
    }

    override suspend fun getSubscriberById(id: UUID): Subscriber? {
        return dao.getSubscriberById(id)
    }

    override suspend fun insertSubscriber(subscriber: Subscriber) {
        dao.insertSubscriber(subscriber)
    }

    override suspend fun deleteSubscriber(subscriber: Subscriber) {
        dao.deleteSubscriber(subscriber)
    }
}