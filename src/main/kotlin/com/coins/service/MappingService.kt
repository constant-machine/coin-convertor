package com.coins.service

import com.coins.model.ConversionModel
import com.fasterxml.jackson.databind.ObjectMapper

interface MappingService {
    fun parseFormData(formInput: String): ConversionModel
    fun parseApiResponse(response: String, currencyName: String): Double
}

class MappingServiceImpl(private val objectMapper: ObjectMapper): MappingService {

     // TODO: mapper per domain, not one mapper for everything

    override fun parseFormData(formInput: String): ConversionModel {
        val propertiesMap: HashMap<String, Any> = HashMap() // TODO: use Map

        formInput.split("&") // TODO: make it readable and not a mess
            .forEach { k -> propertiesMap[k.split("=")[0]] = k.split("=")[1] }
        return objectMapper.convertValue(propertiesMap, ConversionModel::class.java)
    }

    override fun parseApiResponse(response: String, currencyName: String): Double {
        println("!!!" + response)
        return objectMapper.readTree(response).get("data").get(currencyName).asDouble()
    }
}