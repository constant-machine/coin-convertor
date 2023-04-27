package com.coins.service

import com.coins.mapping.ObjectMapping
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

interface ApiClient {
    suspend fun getRate(nameFrom: String, nameTo:String): Double
}

class ClientFCA(private val client: HttpClient = HttpClient(CIO)): ApiClient {

    override suspend fun getRate(nameFrom: String, nameTo:String): Double {

        val response: HttpResponse = client.get("https://api.freecurrencyapi.com/v1/latest") {
            parameter("apikey", System.getenv("apikey"))
            parameter("base_currency", nameFrom)
            parameter("currencies", nameTo)
        }
        return ObjectMapping.parseApiResponse(response.body(), nameTo)
    }
}