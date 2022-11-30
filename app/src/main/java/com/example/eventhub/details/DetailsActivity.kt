package com.example.eventhub.details

import android.widget.Space
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
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
import androidx.compose.ui.res.stringResource
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
        Surface(modifier = Modifier.fillMaxWidth()) {
            Column(modifier.fillMaxWidth().verticalScroll(rememberScrollState())) {
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

                    Text(
                        text = event!!.title,
                        modifier.padding(vertical = 8.dp),
                        fontSize = 38.sp,
                        fontWeight = FontWeight.Light
                    )

                    DetailsRow(detailsTitle = event!!.date, icon=R.drawable.ic_icon_calendar_bg )
                    DetailsRow(detailsTitle = event!!.location, icon=R.drawable.ic_icon_location_bg )

                    Text(
                        text = stringResource(R.string.details_about_event),
                        modifier.padding(vertical = 8.dp),
                        fontSize = 22.sp
                    )
                    Text(
                        fontWeight = FontWeight.ExtraLight,
                        text = event!!.description)

                }
            }

        }
    }
}

@Composable
fun DetailsRow( detailsTitle: String,  modifier: Modifier = Modifier, @DrawableRes icon : Int) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(icon),
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

