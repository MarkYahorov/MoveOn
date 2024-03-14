package com.moveon.network.network.deserializers

import com.google.gson.Gson
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.moveon.network.network.data.CountriesResponse
import com.moveon.network.network.data.CountryItemResponse
import com.moveon.network.network.data.CountryServiceResponse
import java.lang.reflect.Type

class CountryResultDeserializer : JsonDeserializer<CountriesResponse> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): CountriesResponse {
        if (json == null || context == null) {
            throw Exception("country deserialize error")
        }

        val jsonObject = json.asJsonObject

        val resultSet = jsonObject.get("result").asJsonObject.entrySet()
        val resultList = resultSet.map {
            val itemJsonObject = it.value.asJsonObject
            val countryCode = itemJsonObject.get("countryCode").asString
            val name = itemJsonObject.get("name").asString
            val services = itemJsonObject.get("services").asJsonObject.entrySet()
                .map {
                    Gson().fromJson(it.value, CountryServiceResponse::class.java)
                }

            CountryItemResponse(countryCode, name, services)
        }

        return CountriesResponse(resultList)
    }
}