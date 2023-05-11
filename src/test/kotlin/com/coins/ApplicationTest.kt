package com.coins

import io.ktor.client.request.*
import io.ktor.server.testing.*
import kotlin.test.*
import io.ktor.http.*

class ApplicationTest {

    @Test
    fun testApplicationWorks() = testApplication {
        client.post("/convert") {
            setBody("nameFrom=USD&nameTo=EUR&amount=1")
        }.apply {
            assertEquals(HttpStatusCode.OK, status)
        }
    }
}
