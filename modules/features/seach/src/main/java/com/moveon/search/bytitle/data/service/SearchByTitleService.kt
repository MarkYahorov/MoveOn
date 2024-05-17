package com.moveon.search.bytitle.data.service

import com.moveon.core.ResponseResult
import com.moveon.network.network.data.NetworkError
import com.moveon.search.bytitle.data.data.response.SearchByTitleResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchByTitleService {

    @GET("search/title")
    suspend fun searchByTitle(
        @Query("country") country: String,
        @Query("title") title: String
    ): ResponseResult<SearchByTitleResponse, NetworkError>
}