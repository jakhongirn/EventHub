package com.example.eventhub.api

import com.example.eventhub.api.event.EventRequest
import com.example.eventhub.api.event.EventResponse
import com.example.eventhub.api.myResponse.MyItemResponse
import com.example.eventhub.api.myResponse.MyListResponse
import com.example.eventhub.api.myResponse.MyResponse
import retrofit2.http.*

interface EventService {
    @GET("records/all")
    suspend fun getAllEvents(
        @Query("student_id") student_id: String
    ): MyListResponse<EventResponse>

    @GET("records/{record_id}")
    suspend fun getOneEventById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyItemResponse<EventResponse>

    @POST("records")
    suspend fun insertNewEvent(
        @Query("student_id") student_id: String,
        @Body eventRequest: EventRequest
    ): MyResponse

    @PUT("records/{record_id}")
    suspend fun updateOneEventById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String,
        @Body eventRequest: EventRequest
    ): MyResponse

    @DELETE("records/{record_id}")
    suspend fun deleteOneEventById(
        @Path("record_id") record_id: String,
        @Query("student_id") student_id: String
    ): MyResponse

    @DELETE("records/all")
    suspend fun deleteAllEvents(
        @Query("student_id") student_id: String
    ): MyResponse
}