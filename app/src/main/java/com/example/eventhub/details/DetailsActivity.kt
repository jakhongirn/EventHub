package com.example.eventhub.details

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.example.eventhub.R

@Composable
fun DetailsActivity(eventId: String, viewModel: DetailsViewModel = DetailsViewModel(eventId), modifier: Modifier = Modifier) {

    val event by viewModel.eventLiveData.observeAsState()

    if (event != null) {
        Column(modifier.fillMaxWidth()) {
            AsyncImage(
                model = event!!.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
            Column(
                modifier
                    .padding(16.dp)
                    .fillMaxWidth()

            ) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = event!!.title,
                    modifier.padding(vertical = 24.dp),
                    fontSize = 38.sp,
                    fontWeight = FontWeight.Light
                )

                DetailsRowFirst(detailsTitle = event!!.date )
                DetailsRowSecond(detailsTitle = event!!.location )

                Text(
                    text = "About Event",
                    modifier.padding(vertical = 16.dp),
                    fontSize = 22.sp
                )
                Text(
                    fontWeight = FontWeight.ExtraLight,
                    text = event!!.description)

            }
        }

    }
}



@Composable
fun DetailsRowFirst( detailsTitle: String,  modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id=R.drawable.ic_icon_calendar_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(42.dp)

        )
        Column(modifier.padding(horizontal = 12.dp)) {
            Text(text = detailsTitle, fontWeight = FontWeight.Medium )
        }
    }
    Spacer(Modifier.height(20.dp))
}
@Composable
fun DetailsRowSecond( detailsTitle: String,  modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id=R.drawable.ic_icon_location_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(42.dp)

        )
        Column(modifier.padding(horizontal = 12.dp)) {
            Text(text = detailsTitle, fontWeight = FontWeight.Medium )
        }
    }
    Spacer(Modifier.height(20.dp))
}

@Preview(showBackground = true)
@Composable
fun PreviewDetails(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth()) {
        AsyncImage(
            model = "https://www.ruancan.com/wp-content/uploads/2021/05/Google-IO-2021-1.jpg",
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
            )
        Column(
            modifier
                .padding(16.dp)
                .fillMaxWidth()

        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "International Band Music Concert",
                modifier.padding(vertical = 24.dp),
                fontSize = 38.sp,
                fontWeight = FontWeight.Light
            )


            Text(
                text = "About Event",
                modifier.padding(vertical = 16.dp),
                fontSize = 22.sp
            )
            Text(
                fontWeight = FontWeight.ExtraLight,
                text = "Enjoy your favorite dishe and a lovely your friends and family and have a great time. Food from local food trucks will be available for purchase.")

        }
    }
}