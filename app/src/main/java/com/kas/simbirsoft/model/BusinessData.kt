package com.kas.simbirsoft.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.kas.simbirsoft.extensions.getCalendar
import com.kas.simbirsoft.extensions.getDateTime
import kotlinx.parcelize.Parcelize
import java.sql.Date
import java.sql.Timestamp

@Parcelize
data class BusinessData(
    val id: Long,
    @SerializedName("date_start") val dateStart: Long,
    @SerializedName("date_finish") val dateFinish: Long,
    val name: String,
    val description: String
) : Parcelable {
    fun getStartDate() = dateStart.getCalendar().getDateTime()

    fun getFinishDate() = dateFinish.getCalendar().getDateTime()
}
