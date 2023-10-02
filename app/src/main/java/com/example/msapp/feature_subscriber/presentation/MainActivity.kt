package com.example.msapp.feature_subscriber.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.msapp.feature_subscriber.presentation.add_subscriber.components.AddSubscriberScreen
import com.example.msapp.feature_subscriber.presentation.login.LoginScreen
import com.example.msapp.feature_subscriber.presentation.sign_up.SignUpScreen
import com.example.msapp.feature_subscriber.presentation.subscribers.components.SubscribersScreen
import com.example.msapp.feature_subscriber.presentation.util.Screen
import com.example.msapp.ui.theme.MSAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MSAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.LoginScreen.route
                    ) {
                        composable(route = Screen.SubscribersScreen.route) {
                            SubscribersScreen(navController)
                        }
                        composable(route = Screen.LoginScreen.route) {
                            LoginScreen(navController)
                        }

                        composable(route = Screen.SignUpScreen.route) {
                            SignUpScreen(navController)
                        }
                        composable(route = "${Screen.AddSubscriberScreen.route}?subscriberId={subscriberId}",
                            arguments = listOf(
                                navArgument("subscriberId") {
                                    type = NavType.StringType
                                    defaultValue = null
                                    nullable = true
                                }
                            )) {
                            AddSubscriberScreen(navController = navController)
                        }
                    }
                }
            }
        }
    }
}

