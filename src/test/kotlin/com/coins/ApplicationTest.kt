package com.coins

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*
import com.coins.plugins.*

class ApplicationTest {

    @Test
    fun testApplicationWorks() = testApplication {
        application {
            configureTemplating()
        }
        client.post("/convert") {
            setBody("nameFrom=USD&nameTo=EUR&amount=1")
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
