package com.coins.mapping

import com.coins.model.ConversionModel
import com.fasterxml.jackson.databind.ObjectMapper

object ObjectMapping {

    private val objectMapper = ObjectMapper()

    fun parseFormData(formInput: String): ConversionModel {
        val propertiesMap: HashMap<String, Any> = HashMap()

        formInput.split("&")
            .forEach { k -> propertiesMap[k.split("=")[0]] = k.split("=")[1] }
        return objectMapper.convertValue(propertiesMap, ConversionModel::class.java)
    }

    fun parseApiResponse(response: String, currencyName: String): Double {
        return objectMapper.readTree(response).get("data").get(currencyName).asDouble()
    }
}