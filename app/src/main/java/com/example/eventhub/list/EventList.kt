package com.example.eventhub.list

import android.os.Build
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.eventhub.models.EventModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@Composable
fun EventsList(
    viewModel: ListViewModel = ListViewModel(),
    onEventClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
)
{
    val context = LocalContext.current
    Box(modifier.fillMaxSize())
    val events by viewModel.eventsLiveData.observeAsState()

    LazyColumn(modifier= Modifier
        .fillMaxWidth()) {
        events?.let {
            items(items = it.toList(), itemContent = {item ->
                EventItem(event = item, onEventClick)
            })
        }

    }
}

@Composable
fun EventItem(event: EventModel, onEventClick: (String) -> Unit, modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(15.dp, 10.dp, 15.dp, 0.dp)
            .clickable { onEventClick(event.id.toString()) }
    ) {
        Title(title = event.title)
        Description(desc = event.description)
        Date(date = event.date)
        Divider(
            modifier = Modifier
                .padding(top = 10.dp)
                .background(Color.Gray)
        )
    }
}

@Composable
private fun Title(title: String) {
    Text(
        text = title,
        color= MaterialTheme.colors.primary
    )
}

@Composable
private fun Description(desc: String) {
    Text(text = desc, color = MaterialTheme.colors.secondary)
}

@Composable
private fun Date(date: String) {
    val apiDateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dateTime = LocalDate.parse(date, apiDateFormat)
    Text(text = dateTime.toString(), color = MaterialTheme.colors.primary)
}
