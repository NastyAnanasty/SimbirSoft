package com.kas.simbirsoft.source.local

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kas.simbirsoft.R
import com.kas.simbirsoft.model.BusinessData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class BusinessDataSource @Inject constructor(private val context: Context) {
    suspend fun getBusiness(): List<BusinessData> = withContext(Dispatchers.IO) {
        val list = Gson().fromJson<List<BusinessData>>(
            getJson(context),
            object : TypeToken<List<BusinessData>>() {}.type
        )
        return@withContext list
    }

    companion object {
        fun getJson(context: Context): String =
            context.resources.openRawResource(R.raw.test).bufferedReader().use { it.readText() }
    }
}