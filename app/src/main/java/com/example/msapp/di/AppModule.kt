package com.example.msapp.di

import android.app.Application
import androidx.room.Room
import com.example.msapp.feature_subscriber.data.data_source.SubscriberDatabase
import com.example.msapp.feature_subscriber.data.repository.SubscriberRepositoryImpl
import com.example.msapp.feature_subscriber.domain.model.Subscriber
import com.example.msapp.feature_subscriber.domain.repository.SubscriberRepository
import com.example.msapp.feature_subscriber.domain.use_case.DeleteSubscriberUseCase
import com.example.msapp.feature_subscriber.domain.use_case.GetSubscriberByIdUseCase
import com.example.msapp.feature_subscriber.domain.use_case.GetSubscribersUseCase
import com.example.msapp.feature_subscriber.domain.use_case.InsertSubscriberUseCase
import com.example.msapp.feature_subscriber.domain.use_case.SubscriberUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSubscriberDatabase(application: Application): SubscriberDatabase {
        return Room.databaseBuilder(
            application,
            SubscriberDatabase::class.java,
            SubscriberDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideSubscriberRepository(db: SubscriberDatabase): SubscriberRepository {
        return SubscriberRepositoryImpl(db.subscriberDao)
    }


    @Provides
    @Singleton
    fun provideSubscribersUseCases(repository: SubscriberRepository): SubscriberUseCases {
        return SubscriberUseCases(
            getSubscribersUseCase = GetSubscribersUseCase(repository),
            deleteSubscriberUseCase = DeleteSubscriberUseCase(repository),
            insertSubscriberUseCase= InsertSubscriberUseCase(repository),
            getSubscriberByIdUseCase = GetSubscriberByIdUseCase(repository),

        )
    }

}