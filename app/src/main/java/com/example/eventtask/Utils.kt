package com.example.eventtask

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class Utils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedTime(ts: String): String {
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val timestamp = Instant.parse(ts)
        val zonedDateTime = ZonedDateTime.ofInstant(timestamp, ZoneId.of("UTC"))
        val time = zonedDateTime.toLocalTime()

        return time.format(DateTimeFormatter.ofPattern("hh:mm a"))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFormattedDate(inputDate: String): String {
        DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")

        val instant = Instant.parse(inputDate)
        val localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault())

        return outputFormatter.format(localDateTime)
    }
}