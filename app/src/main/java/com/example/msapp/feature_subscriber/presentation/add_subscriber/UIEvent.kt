package com.example.msapp.feature_subscriber.presentation.add_subscriber

/**
 * Created by Stephen Obeng Takyi on 04/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
sealed class UIEvent {
    data class ShowSnackBar(val message: String) : UIEvent()
    object SaveSubscriber: UIEvent()
}
