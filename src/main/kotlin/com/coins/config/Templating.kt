package com.coins.config

import com.coins.service.MappingService
import com.coins.model.ConversionModel
import com.coins.service.ConversionService
import io.ktor.server.thymeleaf.Thymeleaf
import io.ktor.server.thymeleaf.ThymeleafContent
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import io.ktor.server.response.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.configureTemplating() {
    install(Thymeleaf) {
        setTemplateResolver(ClassLoaderTemplateResolver().apply {
            prefix = "templates/thymeleaf/"
            suffix = ".html"
            characterEncoding = "utf-8"
        })
    }

    val conversionService: ConversionService by inject()
    val mappingService: MappingService by inject()
    val defaultModel = ConversionModel("USD", "EUR", "1")

    routing {
        get("/") {
            call.respondRedirect("/convert")
        }
        get("/convert") {
            call.respond(ThymeleafContent("index", mapOf("result" to 0, "conversion" to defaultModel)))
        }
        post("/convert") {
            val result: Double
            try {
                val model = mappingService.parseFormData(call.receiveText())
                result = conversionService.convertCurrencies(model)
                call.respond(ThymeleafContent("index",
                    mapOf("result" to result, "conversion" to model)))
            } catch (ex: Exception) {
                val errorMessage = ex.message.toString()
                call.respond(ThymeleafContent("index",
                    mapOf("result" to "Error happened: $errorMessage", "conversion" to defaultModel)))
            }
        }
    }
}
