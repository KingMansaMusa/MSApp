package com.example.msapp.feature_subscriber.presentation.util

/**
 * Created by Stephen Obeng Takyi on 05/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
sealed class Screen(val route: String){
    object SubscribersScreen: Screen("subscribers_screen")
    object AddSubscriberScreen: Screen("add_subscriber_screen")
    object LoginScreen: Screen("login_screen")
    object SignUpScreen: Screen("sign_up_screen")
}
