package com.coins

import com.coins.service.MappingServiceImpl
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlin.test.*
import kotlin.test.assertEquals

class MappingTest {

    private val mappingService = MappingServiceImpl(jacksonObjectMapper())

    @Test
    fun parseApiResponseTest() {
        val response =
            """{
                "data": {
                "EUR": 0.5109
                }
            }"""
        val rate = mappingService.parseApiResponse(response, "EUR")
        assertEquals(0.5109, rate)
    }

    @Test
    fun sampleClientTest() {
        val formInput = "nameFrom=USD&nameTo=EUR&amount=1"
        val model = mappingService.parseFormData(formInput)
        assertEquals("USD", model.nameFrom)
        assertEquals("EUR", model.nameTo)
        assertEquals("1", model.amount)
    }
}