package com.example.msapp.feature_subscriber.presentation.subscribers.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.msapp.feature_subscriber.domain.util.OrderType
import com.example.msapp.feature_subscriber.domain.util.SubscriberOrder

/**
 * Created by Stephen Obeng Takyi on 04/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */
@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    subscriberOrder: SubscriberOrder = SubscriberOrder.Date(OrderType.Descending),
    onOrderChange: (SubscriberOrder) -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(text = "Name",
                selected = subscriberOrder is SubscriberOrder.Name,
                onSelected = {
                    onOrderChange(SubscriberOrder.Name(subscriberOrder.orderType))
                })

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "Date",
                selected = subscriberOrder is SubscriberOrder.Date,
                onSelected = {
                    onOrderChange(SubscriberOrder.Date(subscriberOrder.orderType))
                })

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "Service Type",
                selected = subscriberOrder is SubscriberOrder.Package,
                onSelected = {
                    onOrderChange(SubscriberOrder.Package(subscriberOrder.orderType))
                })
        }
        Spacer(modifier = Modifier.width(16.dp))

        Row(
            modifier = modifier.fillMaxWidth()
        ) {
            DefaultRadioButton(text = "Ascending",
                selected = subscriberOrder.orderType is OrderType.Ascending,
                onSelected = {
                    onOrderChange(subscriberOrder.copy(OrderType.Ascending))
                })

            Spacer(modifier = Modifier.width(8.dp))

            DefaultRadioButton(text = "Descending",
                selected = subscriberOrder.orderType is OrderType.Descending,
                onSelected = {
                    onOrderChange(subscriberOrder.copy(OrderType.Descending))
                })
        }
    }

}