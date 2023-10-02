package com.example.msapp.feature_subscriber.domain.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID
import javax.annotation.Nonnegative

/**
 * Created by Stephen Obeng Takyi on 03/07/2023,
 * Supertech (STL) Ghana Limited,
 * stephenta@stlghana.com.
 */

@Entity
data class Subscriber(
    @PrimaryKey
    @NonNull
    val id: UUID = UUID.randomUUID(),
    val name: String,
    val phoneNumber: String,
    val serviceType: String,
    val startDate: String,
    val email: String,
    val dob: String,
    val location: String
) {

}

class InvalidSubscriberException(message: String) : Exception(message)
