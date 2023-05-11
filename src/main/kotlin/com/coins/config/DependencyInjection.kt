package com.coins.config

import com.coins.service.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

fun Application.configureDI() {

    val currencyApiUrl = environment.config.property("ktor.currencyClient.url").getString()
    val apiKey = environment.config.property("ktor.currencyClient.api_key").getString()
    println("!!!" + apiKey)

    val coinModule = module {
        single<ConversionService> { FCAConversionService(get()) }
        single { jacksonObjectMapper() }
        single<MappingService> { MappingServiceImpl(get()) }
        single { ClientProperties(currencyApiUrl, apiKey) }
        single<ApiClient> { ClientFCA(get(), get(), get()) }
        single { HttpClient(CIO) }
    }

    install(Koin) {
        modules(coinModule)
    }
}

data class ClientProperties(
    val currencyApiHost: String,
    val apiKey: String
)
