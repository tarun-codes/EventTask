package com.example.eventtask.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class AgendaData(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("user_id") var userId: Int? = null,
    @SerializedName("event_id") var eventId: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("start_date") var startDate: String? = null,
    @SerializedName("end_date") var endDate: String? = null,
    @SerializedName("description") var description: String? = null,
    @SerializedName("sponsor_name") var sponsorName: String? = null,
    @SerializedName("sponsor_img") var sponsorImg: String? = null,
    @SerializedName("location_name") var locationName: String? = null,
    @SerializedName("header_img") var headerImg: String? = null,
    @SerializedName("my_agenda") var myAgenda: Int? = null,
    @SerializedName("attendees") var attendees: ArrayList<Attendees> = arrayListOf(),
    @SerializedName("register_links") var registerLinks: ArrayList<RegisterLinks> = arrayListOf(),
    @SerializedName("agenda_documents") var agendaDocuments: ArrayList<AgendaDocuments> = arrayListOf(),
    @SerializedName("agenda_speakers") var agendaSpeakers: ArrayList<AgendaSpeakers> = arrayListOf()

): Serializable