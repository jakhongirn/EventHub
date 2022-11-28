package com.example.eventhub.list

import android.media.metrics.Event
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

    val moviesLiveData: MutableLiveData<List<EventModel>> by lazy {
        MutableLiveData<List<EventModel>>()
    }

    init {
        getListofEventsFromAPI()
//        deleteAllMovies()
    }

    fun getListofEventsFromAPI() {
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
                                event.date,
                                event.location,
                                event.price,
                                event.imageUrl,
                                event.description
                            )
                        )
                    }

                    moviesLiveData.value = myEvents
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

    }

    fun deleteAllMovies() {
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