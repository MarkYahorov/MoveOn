package com.moveon.network.network.data

import com.google.gson.annotations.SerializedName

data class CountriesResponse(
    @SerializedName("result")
    val result: List<CountryItemResponse>
)

data class CountryItemResponse(
     @SerializedName("countryCode")
    val countryCode: String,
     @SerializedName("name")
    val name: String,
     @SerializedName("services")
    val services: List<CountryServiceResponse>
)

data class CountryServiceResponse(
     @SerializedName("id")
    val id: String,
     @SerializedName("name")
    val name: String,
     @SerializedName("images")
    val images: ServiceImagesResponse
)

data class ServiceImagesResponse(
     @SerializedName("lightThemeImage")
    val lightThemeImage: String,
     @SerializedName("darkThemeImage")
    val darkThemeImage: String
)