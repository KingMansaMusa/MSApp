package com.example.msapp.feature_subscriber.presentation.subscribers.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.msapp.feature_subscriber.domain.model.Subscriber
import kotlin.random.Random

/**
 * Created by Stephen Obeng Takyi on 04/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */

@Composable
fun SubscriberItem(
    subscriber: Subscriber,
    modifier: Modifier,
    onDeleteCLick: () -> Unit
) {
    Card(
        modifier = modifier.padding(10.dp),
        colors = CardDefaults.cardColors(Color.White),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        ),
        shape = RoundedCornerShape(10.dp),

        ) {
        Row(
            modifier = Modifier
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(height = 50.dp, width = 50.dp)
                    .clip(shape = RoundedCornerShape(60.dp))
                    .background(
                        Color(
                            Random.nextFloat(),
                            Random.nextFloat(),
                            Random.nextFloat(),
                            1f
                        )
                    )
                    .weight(0.1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${subscriber.name[0]}",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
            Column(
                modifier = modifier
                    .padding(10.dp)
                    .weight(0.8f),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = subscriber.name,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color.DarkGray
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                ) {
                    Text(
                        modifier = modifier
                            .weight(0.8f)
                            .padding(5.dp),
                        text = subscriber.phoneNumber,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray
                    )

                    Text(
                        modifier = modifier
                            .weight(0.2f)
                            .background(
                                shape = RoundedCornerShape(5.dp),
                                color = if (subscriber.serviceType == "Prepaid") Color.Green.copy(
                                    alpha = 0.1f
                                ) else Color.Blue.copy(
                                    alpha = 0.1f
                                )
                            )
                            .padding(5.dp),
                        text = subscriber.serviceType,
                        textAlign = TextAlign.Center,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (subscriber.serviceType == "Prepaid") Color.Green else Color.Blue,
                    )
                }

            }

            IconButton(
                onClick = onDeleteCLick,
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .weight(0.1f)
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Subscriber",
                    tint = Color.Red
                )
            }

        }
    }
}