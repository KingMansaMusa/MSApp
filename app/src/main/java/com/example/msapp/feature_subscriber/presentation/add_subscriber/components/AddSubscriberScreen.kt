package com.example.msapp.feature_subscriber.presentation.add_subscriber.components

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.msapp.feature_subscriber.presentation.add_subscriber.AddSubscriberEvent
import com.example.msapp.feature_subscriber.presentation.add_subscriber.AddSubscriberViewModel
import com.example.msapp.feature_subscriber.presentation.add_subscriber.UIEvent
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * Created by Stephen Obeng Takyi on 04/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSubscriberScreen(
    navController: NavController,
    viewModel: AddSubscriberViewModel = hiltViewModel()
) {

    val nameState = viewModel.subscriberName.value
    val emailState = viewModel.subscriberEmail.value
    val dobState = viewModel.subscriberDob.value
    val phoneNumberState = viewModel.subscriberPhoneNumber.value
    val locationState = viewModel.subscriberLocation.value
    val serviceTypeState = viewModel.subscriberServiceType.value
    val startDateState = viewModel.subscriberStartDate.value
    val serviceDropdownExpanded = viewModel.serviceDropdownExpanded.value

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var fromDob: Boolean = true

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UIEvent.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(message = event.message)
                }

                is UIEvent.SaveSubscriber -> {
                    navController.navigateUp()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Subscriber Details", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back to Subscribers List",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(MaterialTheme.colorScheme.primary)
            )
        }
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.background)
        ) {


            val mYear: Int = Calendar.getInstance().get(Calendar.YEAR)
            val mMonth: Int = Calendar.getInstance().get(Calendar.MONTH)
            val mDay: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                LocalContext.current,
                { _: DatePicker, sYear: Int, sMonth: Int, sDay: Int ->
                    if (fromDob) viewModel.onEvent(AddSubscriberEvent.EnteredDob("$sDay/${sMonth + 1}/$sYear"))
                    else viewModel.onEvent(AddSubscriberEvent.EnteredStartDate("$sDay/${sMonth + 1}/$sYear"))
                }, mYear, mMonth, mDay
            )


            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = nameState,
                onValueChange = { viewModel.onEvent(AddSubscriberEvent.EnteredName(it)) },
                singleLine = true,
                label = {
                    Text(
                        text = "Subscriber Name",
                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(10.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.None,
                    false,
                    KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = emailState,
                onValueChange = { viewModel.onEvent(AddSubscriberEvent.EnteredEmail(it)) },
                singleLine = true,
                label = {
                    Text(
                        text = "Subscriber Email",
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

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = dobState,
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date Picker",
                        modifier = Modifier.clickable {
                            fromDob = true
                            datePickerDialog.show()
                        }
                    )
                },
                onValueChange = { viewModel.onEvent(AddSubscriberEvent.EnteredDob(it)) },
                singleLine = true,
                label = {
                    Text(
                        text = "Subscriber Date of Birth",
                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(10.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.None,
                    false,
                    KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = phoneNumberState,
                onValueChange = { viewModel.onEvent(AddSubscriberEvent.EnteredPhoneNumber(it)) },
                singleLine = true,
                label = {
                    Text(
                        text = "Subscriber Phone Number",
                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(10.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.None,
                    false,
                    KeyboardType.Phone
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = locationState,
                onValueChange = { viewModel.onEvent(AddSubscriberEvent.EnteredLocation(it)) },
                singleLine = true,
                label = {
                    Text(
                        text = "Subscriber Location",
                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(10.dp)
                    )
                },
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.None,
                    false,
                    KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

//            OutlinedTextField(
//                value = serviceTypeState,
//                onValueChange = { viewModel.onEvent(AddSubscriberEvent.EnteredServiceType(it)) },
//                singleLine = true,
//                label = {
//                    Text(
//                        text = "Subscriber Package",
//                        style = TextStyle(fontWeight = FontWeight.SemiBold),
//                        modifier = Modifier.padding(10.dp)
//                    )
//                },
//                keyboardOptions = KeyboardOptions(
//                    KeyboardCapitalization.None,
//                    false,
//                    KeyboardType.Text
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 10.dp)
//            )
//
//            Spacer(modifier = Modifier.height(8.dp))

            Column(modifier = Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = serviceTypeState,
                    readOnly = true,
                    label = {
                        Text(
                            text = "Subscriber Location",
                            style = TextStyle(fontWeight = FontWeight.SemiBold),
                            modifier = Modifier.padding(10.dp)
                        )
                    },
                    onValueChange = { viewModel.onEvent(AddSubscriberEvent.EnteredServiceType(it)) },
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            imageVector = if (serviceDropdownExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                            contentDescription = "Dropdown Arrows",
                            modifier = Modifier.clickable { viewModel.onEvent(AddSubscriberEvent.ToggleServiceDropdown) },
                        )
                    }
                )

                DropdownMenu(
                    expanded = serviceDropdownExpanded,
                    onDismissRequest = { viewModel.onEvent(AddSubscriberEvent.ToggleServiceDropdown) },
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    listOf("Prepaid", "PostPaid").forEach {
                        DropdownMenuItem(
                            text = { Text(text = it) },
                            onClick = { viewModel.onEvent(AddSubscriberEvent.EnteredServiceType(it)) },
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = startDateState,
                onValueChange = { viewModel.onEvent(AddSubscriberEvent.EnteredStartDate(it)) },
                singleLine = true,
                readOnly = true,
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Date Picker",
                        modifier = Modifier.clickable {
                            fromDob = false
                            datePickerDialog.show()
                        }
                    )
                },
                label = {
                    Text(
                        text = "Subscriber Start Date",
                        style = TextStyle(fontWeight = FontWeight.SemiBold),
                        modifier = Modifier.padding(10.dp),
                    )
                },
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.None,
                    false,
                    KeyboardType.Text
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

//            OutlinedTextField(
//                value = startDateState,
//                onValueChange = { viewModel.onEvent(AddSubscriberEvent.EnteredStartDate(it)) },
//                singleLine = true,
//                label = {
//                    Text(
//                        text = "Subscriber Start Date",
//                        style = TextStyle(fontWeight = FontWeight.SemiBold),
//                        modifier = Modifier.padding(10.dp)
//                    )
//                },
//                keyboardOptions = KeyboardOptions(
//                    KeyboardCapitalization.None,
//                    false,
//                    KeyboardType.Text
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 10.dp)
//            )
//
//            Spacer(modifier = Modifier.height(16.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                onClick = {
                    scope.launch {
                        viewModel.onEvent(AddSubscriberEvent.SaveSubscriber)
                    }
                }) {
                Text(text = "Save", Modifier.padding(10.dp))
            }

        }
    }

}