package com.kas.simbirsoft.model

import com.kas.simbirsoft.source.local.BusinessDataSource
import javax.inject.Inject

class BusinessRepository @Inject constructor(private val dataSource: BusinessDataSource) {

    suspend fun getBusiness() = dataSource.getBusiness()
}