package com.moveon.network.network

import com.moveon.core.ResponseResult
import com.moveon.network.network.data.CountriesResponse
import com.moveon.network.network.data.NetworkError
import retrofit2.http.GET

interface CountriesService {

    @GET("countries")
    suspend fun getCountries(): ResponseResult<CountriesResponse, NetworkError>
}