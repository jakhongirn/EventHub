package com.example.eventhub.utils

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
private fun Date(date: String) {
    val apiDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dateTime = LocalDate.parse(date, apiDateFormat)
    Text(text = dateTime.toString(), color = MaterialTheme.colors.primary)
}