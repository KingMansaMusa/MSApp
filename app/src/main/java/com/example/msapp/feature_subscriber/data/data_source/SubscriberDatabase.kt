package com.example.msapp.feature_subscriber.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.msapp.feature_subscriber.domain.model.Subscriber

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */

@Database(entities = [Subscriber::class], version = 1)
abstract class SubscriberDatabase: RoomDatabase() {

    companion object{

        const val DATABASE_NAME: String = "subsscriber_db"

    }

    abstract val subscriberDao: SubscriberDao

}