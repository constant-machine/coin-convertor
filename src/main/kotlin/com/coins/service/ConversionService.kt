package com.coins.service

import com.coins.model.ConversionModel
import org.koin.core.component.KoinComponent

interface ConversionService {
    suspend fun convertCurrencies(queryData: ConversionModel): Double
}

class FCAConversionService(private val client: ApiClient) : ConversionService, KoinComponent {

    override suspend fun convertCurrencies(queryData: ConversionModel): Double {
        val rate = client.getRate(queryData.nameFrom, queryData.nameTo)
        return rate * queryData.amount.toDouble()
    }
}