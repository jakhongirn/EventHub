package com.example.eventhub.details

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eventhub.api.RetrofitInstance
import com.example.eventhub.api.event.EventRequest
import com.example.eventhub.api.event.EventResponse
import com.example.eventhub.api.myResponse.MyItemResponse
import com.example.eventhub.api.myResponse.MyResponse
import com.example.eventhub.models.EventModel
import com.example.eventhub.utils.dotenv
import kotlinx.coroutines.launch

class DetailsViewModel(eventId: String) : ViewModel(){

    val eventLiveData: MutableLiveData<EventModel> by lazy {
        MutableLiveData<EventModel>()
    }
    init {
        getEventByIdFromApi(eventId)
        //deleteOneMovieById()
        //editEventById()
    }

    private fun getEventByIdFromApi(eventId: String) {
        viewModelScope.launch {
            try {
                val response: MyItemResponse<EventResponse> =
                    RetrofitInstance.eventService.getOneEventById(eventId, dotenv.STUDENT_ID)
                val eventFromApi = response.data

                if (eventFromApi != null) {
                    eventLiveData.value = EventModel(
                        eventFromApi.id,
                        eventFromApi.title,
                        eventFromApi.location,
                        eventFromApi.price,
                        eventFromApi.imageURL,
                        eventFromApi.description,
                        eventFromApi.date
                    )
                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    fun editEventById(eventId: String, eventRequest: EventRequest) {
        viewModelScope.launch {
            try {
                val response: MyResponse = RetrofitInstance.eventService.updateOneEventById(
                    eventId,
                    dotenv.STUDENT_ID,
                    eventRequest
                )
                Log.d("Update response: ", response.toString())
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteOneMovieById(eventId: String) {
        viewModelScope.launch {
            try {
                val response: MyResponse = RetrofitInstance.eventService.deleteOneEventById(
                    eventId,
                    dotenv.STUDENT_ID
                )
                Log.d("Delete response: ", response.toString())
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }
}