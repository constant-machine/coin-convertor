package com.coins.service

import com.coins.config.ClientProperties
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

interface ApiClient {
    suspend fun getRate(nameFrom: String, nameTo:String): Double
}

class ClientFCA(
    private val client: HttpClient,
    private val clientConfig: ClientProperties,
    private val mappingService: MappingService
): ApiClient {

    override suspend fun getRate(nameFrom: String, nameTo:String): Double {

        val response: HttpResponse = client.get(clientConfig.currencyApiHost) {
            parameter("apikey", clientConfig.apiKey)
            parameter("base_currency", nameFrom)
            parameter("currencies", nameTo)
        }
        return mappingService.parseApiResponse(response.body(), nameTo)
    }
}