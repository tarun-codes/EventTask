package com.example.eventtask.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Attendees(

    @SerializedName("image") var image: String? = null,
    @SerializedName("company_name") var companyName: String? = null,
    @SerializedName("name") var name: String? = null

): Serializable