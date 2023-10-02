package com.example.msapp.feature_subscriber.presentation.login

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.msapp.R
import com.example.msapp.feature_subscriber.presentation.add_subscriber.AddSubscriberEvent
import com.example.msapp.feature_subscriber.presentation.util.Screen
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

/**
 * Created by Stephen Obeng Takyi on 12/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {

    val auth = Firebase.auth
    var emailState by remember { mutableStateOf("") }
    var passwordState by remember { mutableStateOf("") }

    val scope = rememberCoroutineScope()

    var currentUser = auth.currentUser

    val snackBarHostState = remember {SnackbarHostState()}


    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) }
    ) { paddedValues ->
        Column(
            modifier = Modifier
                .padding(paddedValues)
                .fillMaxSize()
        ) {

            Box(modifier = Modifier.fillMaxHeight(0.5f)) {
                Image(
                    painter = painterResource(id = R.drawable.login_image),
                    contentDescription = "Login Image",
                    contentScale = ContentScale.FillBounds
                )
            }

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            ) {
                OutlinedTextField(
                    value = emailState,
                    onValueChange = { emailState = it },
                    singleLine = true,
                    label = {
                        Text(
                            text = "Email",
                            style = TextStyle(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.padding(10.dp)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        KeyboardCapitalization.None,
                        false,
                        KeyboardType.Email
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )

                OutlinedTextField(
                    value = passwordState,
                    onValueChange = { passwordState = it },
                    singleLine = true,
                    label = {
                        Text(
                            text = "Password",
                            style = TextStyle(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.padding(10.dp)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        KeyboardCapitalization.None,
                        false,
                        KeyboardType.Password
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    modifier = Modifier
                        .fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                    onClick = {

                        auth.signInWithEmailAndPassword(emailState, passwordState)
                            .addOnCompleteListener() { task ->
                                if (task.isSuccessful) {
                                    Log.d("LOGIN SCREEN", "SIGN IN WITH EMAIL SUCCESSFUL")
                                    currentUser = auth.currentUser
                                    navController.navigate(Screen.SubscribersScreen.route)
                                } else {
                                    Log.d("LOGIN SCREEN", "SIGN IN WITH EMAIL FAILED")
                                    Log.w(
                                        "LOGIN SCREEN",
                                        "signInWithEmail:failure",
                                        task.exception
                                    )

                                    scope.launch {
                                        snackBarHostState.showSnackbar(message = "Login Failed")
                                    }
                                }
                            }
                    }) {
                    Text(text = "Login", Modifier.padding(10.dp))
                }

                Spacer(modifier = Modifier.height(16.dp))


                Row(
                    modifier = Modifier
                        .padding(horizontal = 16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Don't have an account?", modifier = Modifier.padding(end = 4.dp))
                    Text(
                        modifier = Modifier.clickable { navController.navigate(Screen.SignUpScreen.route) },
                        text = "Sign Up Here",
                        style = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold)

                    )
                }
            }

        }

    }


}