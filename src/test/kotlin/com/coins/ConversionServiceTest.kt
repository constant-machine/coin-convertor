package com.coins

import com.coins.model.ConversionModel
import kotlin.test.*
import io.ktor.http.*
import com.coins.service.ClientFCA
import com.coins.service.FCAConversionService
import io.ktor.client.*
import io.ktor.client.engine.mock.*
import io.ktor.utils.io.*
import kotlinx.coroutines.runBlocking

class ConversionServiceTest {

    @Test
    fun shouldCallDownstreamAndReturnRate() {
        runBlocking {
            val mockEngine = MockEngine { request ->
                respond(
                    content = ByteReadChannel("""{"data": {"EUR": 0.5109}}"""),
                    status = HttpStatusCode.OK,
                    headers = headersOf(HttpHeaders.ContentType, "application/json")
                )
            }
            val mockClient = ClientFCA(HttpClient(mockEngine))
            val clientFCA = FCAConversionService(mockClient)

            val defaultModel = ConversionModel("USD", "EUR", "1")
            assertEquals("0.5109", clientFCA.convertCurrencies(defaultModel).toString())
        }
    }
}