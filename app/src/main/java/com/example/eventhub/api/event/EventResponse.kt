package com.example.eventhub.api.event

import com.google.gson.annotations.SerializedName
import java.net.URL


data class EventResponse (
    @SerializedName("id")
    val id: Int,
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