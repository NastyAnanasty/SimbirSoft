package com.kas.simbirsoft.model

import com.kas.simbirsoft.extensions.getCalendar
import com.kas.simbirsoft.extensions.getDateTime
import java.util.*

data class TimeRange(
    val startTime: Calendar,
    val endTime: Calendar,
    var business: BusinessData?,
) {
    var isStartInRange: Boolean = false
    var isEndInRange: Boolean = false


    constructor(startTime: Calendar, endTime: Calendar) : this(startTime, endTime, null)

    fun addBusiness(business: BusinessData) {
        val startInRange = business.dateStart.getCalendar().timeInMillis in getRange()
        val endInRange = business.dateFinish.getCalendar().timeInMillis in getRange()
        val allInRange =
            business.dateStart.getCalendar().timeInMillis <= startTime.timeInMillis && business.dateFinish.getCalendar().timeInMillis >= endTime.timeInMillis
        if (startInRange || endInRange || allInRange) {
            this.business = business
        }
        isStartInRange = startInRange
        isEndInRange = endInRange
    }

    fun addBusiness(list: List<BusinessData>) {
        this.business = null
        list.forEach { addBusiness(it) }
    }

    private fun getRange(): LongRange = startTime.timeInMillis..endTime.timeInMillis

    fun getTime(): String = " ${startTime.getDateTime()} - ${endTime.getDateTime()}"
}
