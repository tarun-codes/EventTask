package com.example.eventtask.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class AgendaResponse(

    @SerializedName("replyCode") var replyCode: String? = null,
    @SerializedName("replyMsg") var replyMsg: String? = null,
    @SerializedName("data") var data: AgendaData? = AgendaData(),
    @SerializedName("cmd") var cmd: String? = null,
    @SerializedName("imgPath") var imgPath: String? = null,
    @SerializedName("attendeeImgPath") var attendeeImgPath: String? = null,
    @SerializedName("speakerImgPath") var speakerImgPath: String? = null

): Serializable