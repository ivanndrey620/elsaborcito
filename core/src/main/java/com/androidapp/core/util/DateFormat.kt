package com.androidapp.core.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 *  Created by Iván Bolaños on 18/06/2021
 */
sealed class DateFormat(val format: String) {

    object FullTime : DateFormat("yyyyMMdd HH:mm:ss")

    object FullDateNoHour : DateFormat("yyyyMMdd")

    object PrettyDate : DateFormat("yyyy-MM-dd")

    object PrettyDateAndTime : DateFormat("yyyy-MM-dd_HH:mm:ss")

    object PrettyTime : DateFormat("HH:mm:ss")

    object PrettyTimeNoSeconds : DateFormat("HH:mm")

    object PrettyTimeMinSeconds : DateFormat("mm:ss")

    object JustDateWithUnderscore : DateFormat("yyyy-MM-dd HH:mm:ss")

    object PrettyFullDate : DateFormat("yyyy-MM-dd HH:mm:ss")

    fun switchTo(date: String, newFormat: DateFormat): String {
        val parsedDate: Date = SimpleDateFormat(format, Locale.getDefault()).parse(date)
        return SimpleDateFormat(newFormat.format, Locale.getDefault()).format(parsedDate)
    }
}
