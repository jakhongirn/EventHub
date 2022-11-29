package com.example.eventhub.addEvent

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventhub.api.RetrofitInstance
import com.example.eventhub.api.event.EventRequest
import com.example.eventhub.api.myResponse.MyResponse
import com.example.eventhub.utils.dotenv
import kotlinx.coroutines.launch

class AddEventViewModel : ViewModel() {
    val insertEventResponse: MutableLiveData<MyResponse> by lazy {
        MutableLiveData<MyResponse>()
    }
    fun saveNewEventToApi(event: EventRequest) {
        viewModelScope.launch {
            try {
                val response: MyResponse = RetrofitInstance.eventService.insertNewEvent(
                    dotenv.STUDENT_ID,
                    event
                )
                insertEventResponse.value = response
                Log.d("Update response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}