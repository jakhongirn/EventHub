package com.example.eventhub.api.event

import com.google.gson.annotations.SerializedName
import java.net.URL
import java.text.DateFormat
import java.util.*

data class EventRequest (
    @SerializedName("title")
    val title: String,
    @SerializedName("type") //Keeping location value inside a "type" field in the api.
    val location: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("url")
    val imageUrl: URL,
    @SerializedName("description")
    val description: String,
    @SerializedName("date")
    val date: String,
)