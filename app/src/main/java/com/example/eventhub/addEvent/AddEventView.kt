package com.example.eventhub.addEvent

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.inputmethodservice.Keyboard
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.example.eventhub.R
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.net.toUri
import androidx.core.text.isDigitsOnly
import com.example.eventhub.MainActivity
import com.example.eventhub.api.event.EventRequest
import com.example.eventhub.api.myResponse.MyResponse
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.net.URL


@Composable
fun AddEventView(viewModel: AddEventViewModel = AddEventViewModel()) {
    val context = LocalContext.current

    val title  =  remember { mutableStateOf("")}
    val location = remember { mutableStateOf("")}
    val price = remember { mutableStateOf("")}
    val imageURL = remember { mutableStateOf("")}
    val description = remember { mutableStateOf("")}
    val date = remember { mutableStateOf("")}

    val isProgressVisible = remember { mutableStateOf(false)}

    val response by viewModel.insertEventResponse.observeAsState()
    val isChecking = remember { mutableStateOf(true)}
//   if (isChecking.value) {
//       viewModel.saveNewEventToApi(
//           EventRequest(
//               "alsjkd",
//               "kasdhf",
//               12.5,
//               "kasldksa",
//               "ajdskfj;das",
//               "2022-12-12 00:00:00"
//           )
//       )
//       isChecking.value = false
//   }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        StringInput(
            value = title.value,
            onValueChange = { title.value = it},
            label = "Event title",
            placeholder = "e.g. Google I/O"
        )
        StringInput(value = date.value,
            onValueChange = {date.value = it} ,
            label = "Date & Time",
            placeholder = "e.g. 2022-12-31"
        )
        StringInput(
            value = location.value,
            onValueChange = { location.value = it},
            label = "Location",
            placeholder = "e.g. Tashkent, Uzbekistan"
        )
        
        PriceInput(
                value = price.value,
                onValueChange = { price.value = it},
                label = "Price",
                placeholder = "e.g. 9.89",
                fractionNumber = 0.3F
        )
        StringInput(
            value = imageURL.value,
            onValueChange = { imageURL.value = it},
            label = "Image Link",
            placeholder = "e.g. 'https://www.google.com/images...'"
        )
        StringInput(
            value = description.value,
            onValueChange = { description.value = it},
            label = "Description",
            placeholder = "e.g. 'Annual developer conference held by Google in Mountain View, California.'",
            height = 120
        )

        Spacer(Modifier.height(16.dp))

        val validationMsg = stringResource(id = R.string.validation_msg)

        AddEventButton {
            Log.d(title.value, date.value)
            Log.d(location.value, price.value)
//
            if (isInputValid(title.value, description.value, date.value, location.value, imageURL.value)) {
                viewModel.saveNewEventToApi(
                    EventRequest(
                        title = title.value,
                        location = location.value,
                        description = description.value,
                        imageURL = imageURL.value,
                        date = date.value,
                        price = price.value.toDouble()
                    )
                )

                isProgressVisible.value = true
                isChecking.value = false
               Log.d("success", "success")
            }
            else {
//                val toast = Toast.makeText(context, validationMsg, Toast.LENGTH_SHORT)
//                toast.setGravity(Gravity.CENTER, 0, 0)
//                toast.show()
                Log.d("fail","fail")
            }
            }
        }


    response?.let { ProgressToastShow(response = it, isVisible = isProgressVisible.value, context) }

}

@Composable
private fun StringInput(value: String, onValueChange: (String) -> Unit, label: String, placeholder: String, height: Int = 56, fractionNumber: Float = 1F, keyboardType: String = "KeyboardType.Text") {
    Column(modifier = Modifier) {
        Text(text = label, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(4.dp))
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(fractionNumber)
                .padding(vertical = 4.dp)
                .height((height).dp),

            shape = RoundedCornerShape(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.DarkGray,
                unfocusedBorderColor = Color.LightGray
            ),
            value = value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            onValueChange = { onValueChange(it) },
            placeholder = { Text(placeholder)},

        )
        Spacer(Modifier.height(20.dp))
    }

}
@Composable
private fun PriceInput(value: String, onValueChange: (String) -> Unit, label: String, placeholder: String, height: Int = 56, fractionNumber: Float = 1F) {
    Column(modifier = Modifier) {
        Text(text = label, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = "$", style=MaterialTheme.typography.h6, modifier = Modifier.padding(8.dp))
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(fractionNumber)
                    .padding(vertical = 4.dp)
                    .height((height).dp),

                shape = RoundedCornerShape(16.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = Color.DarkGray,
                    unfocusedBorderColor = Color.LightGray
                ),
                value = value,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                onValueChange = { onValueChange(it) },
                placeholder = { Text(placeholder)},

                )
        }
        Spacer(Modifier.height(20.dp))
    }

}


@Composable
private fun AddEventButton(onClick: () -> Unit) {

    Button(
        onClick = {
            onClick()
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(75.dp)
            .padding(vertical = 16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.save_data_button)
        )
    }
}

private fun isInputValid(
    title: String,
    description: String,
    date: String,
    location: String,
    imageURL: String
): Boolean {
    if (title.isBlank() || description.isBlank() || date.isBlank()) return false

    if (location.isBlank() || imageURL.isBlank()) return false

    return true
}


@Composable
private fun ProgressToastShow(response: MyResponse, isVisible: Boolean, context: Context) {
    if (isVisible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Text(
                modifier = Modifier
                    .background(colorResource(id = R.color.save_event_color))
                    .padding(20.dp)
                    .align(Alignment.Center),
                fontSize = 25.sp,
                text =
                if (response.status.isEmpty()) stringResource(id = R.string.add_new_in_progress_mgs) //by default status is "", so if it is empty that means network request hasn't returned a response yet
                else if (response.status == "OK") stringResource(id = R.string.add_new_saved_successfully_msg)
                else stringResource(id = R.string.add_new_failed_to_save_msg)
            )
        }

        context.startActivity(Intent(context, MainActivity::class.java))
    }
}
