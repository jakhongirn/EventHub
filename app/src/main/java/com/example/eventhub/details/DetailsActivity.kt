package com.example.eventhub.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier

@Composable
fun DetailsActivity(eventId: String, viewModel: DetailsViewModel = DetailsViewModel(eventId), modifier: Modifier = Modifier) {

    val event by viewModel.eventLiveData.observeAsState()

    if (event != null) {
        Column(
            modifier
                .fillMaxWidth()
        ) {
            Text(text = event!!.title)
            Text(text = event!!.description)
        }
    }
}