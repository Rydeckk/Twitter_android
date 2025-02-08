package com.example.twitter_like.utils

import java.text.SimpleDateFormat

fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
    inputFormat.timeZone = java.util.TimeZone.getTimeZone("UTC")

    val outputFormat = SimpleDateFormat("MMM d, yyyy", java.util.Locale.ENGLISH)

    val date = inputFormat.parse(inputDate) ?: return inputDate
    return outputFormat.format(date)
}
