package com.example.msapp.feature_subscriber.domain.use_case

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
data class SubscriberUseCases(
    val getSubscribersUseCase: GetSubscribersUseCase,
    val deleteSubscriberUseCase: DeleteSubscriberUseCase,
    val insertSubscriberUseCase: InsertSubscriberUseCase,
    val getSubscriberByIdUseCase: GetSubscriberByIdUseCase
)
