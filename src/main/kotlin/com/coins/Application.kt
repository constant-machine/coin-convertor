package com.coins

import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.coins.config.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureDI()
    configureTemplating()
    configureSerialization()
}
