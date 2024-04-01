package com.moveon.detail.data.service

import com.moveon.core.ResponseResult
import com.moveon.detail.data.response.DetailResponse
import com.moveon.network.network.data.NetworkError
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailService {

    @GET("get")
    suspend fun fetchDetail(
        @Query("imdb_id") imdbId: String?,
        @Query("tmdb_id") tmdbId: String?
    ): ResponseResult<DetailResponse, NetworkError>
}