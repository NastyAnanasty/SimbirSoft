package com.kas.simbirsoft.extensions

import java.sql.Timestamp
import java.util.*

fun Long.getCalendar(): Calendar {
    val date: Calendar = Calendar.getInstance()
    date.timeInMillis = this * 1000L
    return date
}