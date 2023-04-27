package com.coins.plugins

import com.coins.service.ApiClient
import com.coins.service.ClientFCA
import com.coins.service.ConversionService
import com.coins.service.FCAConversionService
import io.ktor.server.application.*
import org.koin.dsl.module
import org.koin.ktor.plugin.Koin

val coinModule = module {
    single { FCAConversionService(get()) as ConversionService }
    single { ClientFCA() as ApiClient }
}

fun Application.configureDI() {
    install(Koin) {
        modules(coinModule)
    }
}