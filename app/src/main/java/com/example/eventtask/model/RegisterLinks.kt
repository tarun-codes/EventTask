package com.example.eventtask.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class RegisterLinks(

    @SerializedName("register_link") var registerLink: String? = null,
    @SerializedName("register_text") var registerText: String? = null

): Serializable