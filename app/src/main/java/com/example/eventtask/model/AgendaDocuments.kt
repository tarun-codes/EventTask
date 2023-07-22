package com.example.eventtask.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class AgendaDocuments(

    @SerializedName("document_name") var documentName: String? = null,
    @SerializedName("document_file") var documentFile: String? = null

): Serializable