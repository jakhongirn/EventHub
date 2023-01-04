package com.example.eventhub.list

import com.example.eventhub.R
import android.os.Build
import android.widget.ListView
import androidx.annotation.DrawableRes
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eventhub.models.EventModel
import com.example.eventhub.ui.theme.EventHubTheme
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

    Column() {

            Surface(
                shape = RoundedCornerShape(bottomEnd = 12.dp, bottomStart = 12.dp),
                elevation = 8.dp,
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(R.color.primary_purple))
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Icon(Icons.Rounded.Menu, contentDescription = null, tint = colorResource(R.color.white))
                    Text(stringResource(R.string.explore_title_name), color= colorResource(R.color.white), style = MaterialTheme.typography.h6)
                    Icon(Icons.Rounded.Search, contentDescription = null, tint = colorResource(R.color.white))
                }
            }
        LazyColumn(modifier= Modifier
            .fillMaxWidth()) {
            events?.let {
                items(items = it.toList(), itemContent = {item ->
                    UpcomingEventCard(event = item, onEventClick)
                })
            }

        }
    }
}



@Composable
fun UpcomingEventCard(
    event: EventModel,
    onEventClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 8.dp,
        modifier = Modifier.padding(vertical = 8.dp)
    ) {


    Column(
        modifier
            .background(color = Color.White)
            .padding(12.dp)
            .clickable { onEventClick(event.id.toString()) }

    ) {
        AsyncImage(
            model = event.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(16.dp))


        )
        Column(
            modifier.padding(horizontal = 12.dp),
        ) {

            Text(
                text = event.title,
                style = MaterialTheme.typography.h5,
                modifier = Modifier
                    .padding(top = 12.dp, bottom = 20.dp)
                ,
                fontWeight = FontWeight.Medium
            )
            RowDetail(drawable = R.drawable.ic_icon_calendar, detail =event.date )
            RowDetail(drawable = R.drawable.ic_icon_location, detail =event.location )

            }
        }
    }
}


@Composable
fun RowDetail(modifier: Modifier = Modifier, @DrawableRes drawable: Int, detail: String) {
    Row(modifier.padding(vertical = 4.dp), verticalAlignment = Alignment.CenterVertically) {
        Icon(painter = painterResource(drawable), contentDescription = null, tint = colorResource(
            R.color.primary_blue
        ))
        Text(
            text = detail,
            modifier = Modifier.padding(horizontal = 10.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreviewList(modifier: Modifier = Modifier) {
//    EventHubTheme() {
//      RowDetail()
//    }
//}