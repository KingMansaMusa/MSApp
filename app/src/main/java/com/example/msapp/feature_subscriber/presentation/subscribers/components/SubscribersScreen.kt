package com.example.msapp.feature_subscriber.presentation.subscribers.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.msapp.feature_subscriber.presentation.subscribers.SubscriberViewModel
import com.example.msapp.feature_subscriber.presentation.subscribers.SubscribersEvent
import com.example.msapp.feature_subscriber.presentation.util.Screen
import com.example.msapp.ui.theme.SubBlue
import kotlinx.coroutines.launch

/**
 * Created by Stephen Obeng Takyi on 04/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubscribersScreen(
    navController: NavController,
    viewModel: SubscriberViewModel = hiltViewModel()
) {
    val state = viewModel.state.value
    val scope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(

        snackbarHost = { SnackbarHost(snackBarHostState) },
        topBar = {
            TopAppBar(
                title = { Text(text = "Subscribers List", color = Color.White) },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    IconButton(onClick = { viewModel.onEvent(SubscribersEvent.ToggleOrderSection) }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Sort",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(Screen.AddSubscriberScreen.route) },
                containerColor = MaterialTheme.colorScheme.primary,
                shape = CircleShape
            ) {

                Icon(imageVector = Icons.Default.Add, contentDescription = "Add Subscriber")
            }
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AnimatedVisibility(
                visible = state.isOderSectionVisible,
                enter = fadeIn() + slideInVertically(),
                exit = fadeOut() + slideOutVertically()
            ) {
                OrderSection(
                    onOrderChange = {
                        viewModel.onEvent(SubscribersEvent.Order(it))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    subscriberOrder = state.subscriberOrder
                )
            }

            Spacer(modifier = Modifier.padding(8.dp))

            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.subscribers) { subscriber ->
                    SubscriberItem(
                        subscriber = subscriber,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    Screen.AddSubscriberScreen.route
                                            + "?subscriberId=${subscriber.id}"
                                )
                            },
                        onDeleteCLick = {
                            viewModel.onEvent(SubscribersEvent.DeleteSubscriber(subscriber))
                            scope.launch {
                                val result = snackBarHostState.showSnackbar(
                                    message = "Subscriber Deleted",
                                    actionLabel = "Undo",
                                )

                                if (result == SnackbarResult.ActionPerformed) {
                                    viewModel.onEvent(SubscribersEvent.RestoreSubscriber)
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }

        }
    }
}