package com.example.eventtask.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class AgendaSpeakers(

    @SerializedName("name") var name: String? = null,
    @SerializedName("title") var title: String? = null,
    @SerializedName("company_name") var companyName: String? = null,
    @SerializedName("image") var image: String? = null

): Serializable