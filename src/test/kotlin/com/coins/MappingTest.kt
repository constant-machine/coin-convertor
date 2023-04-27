package com.coins

import com.coins.mapping.ObjectMapping
import kotlin.test.*
import kotlin.test.assertEquals

class MappingTest {

    @Test
    fun parseApiResponseTest() {
        val response =
            """{
                "data": {
                "EUR": 0.5109
                }
            }"""
        val rate = ObjectMapping.parseApiResponse(response, "EUR")
        assertEquals(0.5109, rate)
    }

    @Test
    fun sampleClientTest() {
        val formInput = "nameFrom=USD&nameTo=EUR&amount=1"
        val model = ObjectMapping.parseFormData(formInput)
        assertEquals("USD", model.nameFrom)
        assertEquals("EUR", model.nameTo)
        assertEquals("1", model.amount)
    }
}