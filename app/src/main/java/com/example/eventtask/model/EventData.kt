package com.example.eventtask.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class EventData(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("start_date") var startDate: String? = null,
    @SerializedName("end_date") var endDate: String? = null,
    @SerializedName("my_agenda") var myAgenda: Int? = null,
    @SerializedName("attendees") var attendees: ArrayList<Attendees> = arrayListOf()

): Serializable