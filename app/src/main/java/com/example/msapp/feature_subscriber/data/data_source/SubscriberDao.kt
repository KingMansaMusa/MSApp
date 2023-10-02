package com.example.msapp.feature_subscriber.data.data_source

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.msapp.feature_subscriber.domain.model.Subscriber
import kotlinx.coroutines.flow.Flow
import java.util.UUID

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */

@Dao
interface SubscriberDao {

    @Query("SELECT * FROM subscriber")
    fun getSubscribers(): Flow<List<Subscriber>>

    @Query("SELECT * FROM subscriber WHERE id = :id")
    suspend fun getSubscriberById(id: UUID): Subscriber?

    @Upsert
    suspend fun insertSubscriber(subscriber: Subscriber)

    @Delete
    suspend fun deleteSubscriber(subscriber: Subscriber)

}