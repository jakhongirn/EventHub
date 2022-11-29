package com.example.eventhub.models

import java.net.URL
import java.util.*

data class EventModel (
    val id: Int,
    val title: String,
    val location: String,
    val price: Double,
    val imageUrl: URL,
    val description: String,
    val date: String
)