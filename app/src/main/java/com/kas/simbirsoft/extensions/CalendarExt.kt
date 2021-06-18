package com.kas.simbirsoft.extensions

import java.text.SimpleDateFormat
import java.util.*


fun Calendar.timeInSeconds() = this.timeInMillis / 1000

fun Calendar.getDateTime(): String = SimpleDateFormat("HH:mm", Locale.ROOT).format(this.time)

fun Calendar.getDate(): String = SimpleDateFormat("dd.MM.yyyy", Locale.ROOT).format(this.time)

fun Calendar.isSameDate(date: Calendar): Boolean =
    this.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR) &&
            this.get(Calendar.YEAR) == date.get(Calendar.YEAR)

fun setDateTime(date: Calendar, hours: Int = 0, minutes: Int = 0): Calendar {
    val newDate = date.clone() as Calendar
    return newDate.apply {
        this[Calendar.HOUR_OF_DAY] = hours
        this[Calendar.MINUTE] = minutes
    }
}