package com.example.eventtask.retrofit

import com.example.eventtask.model.AgendaResponse
import com.example.eventtask.model.Events
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("demo_agneda_list")
    fun postAgenda(@Body body: PostRequestBody): Call<Events>


    @GET("demo_agenda_detail?sid=1&eid=1989&pid=117195")
    fun getAgendaDetails(@Query("aid") aid: String): Call<AgendaResponse>
}