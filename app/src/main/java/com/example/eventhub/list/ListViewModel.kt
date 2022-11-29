package com.example.eventhub.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventhub.models.EventModel
import com.example.eventhub.api.RetrofitInstance
import com.example.eventhub.api.event.EventResponse
import com.example.eventhub.api.myResponse.MyListResponse
import com.example.eventhub.api.myResponse.MyResponse

import kotlinx.coroutines.launch
import java.lang.Exception

class ListViewModel : ViewModel() {

    val eventsLiveData: MutableLiveData<List<EventModel>> by lazy {
        MutableLiveData<List<EventModel>>()
    }

    init {
        getListOfEventsFromAPI()
//        deleteAllEvents()
    }

    fun getListOfEventsFromAPI() {
        viewModelScope.launch {
            try {
                val response: MyListResponse<EventResponse> =
                    RetrofitInstance.eventService.getAllEvents("00011843")
                val eventsFromResponse = response.data

                if (eventsFromResponse != null) {
                    val myEvents = mutableListOf<EventModel>()

                    for (event in eventsFromResponse) {
                        myEvents.add(
                            EventModel(
                                event.id,
                                event.title,
//                                event.location,
                                event.price,
                                event.imageUrl,
                                event.description
                            )
                        )
                    }

                    eventsLiveData.value = myEvents
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun deleteAllEvents() {
        viewModelScope.launch {
            try {

                val response: MyResponse = RetrofitInstance.eventService.deleteAllEvents(
                    "00011843"
                )

                Log.d("Delete_response", response.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}