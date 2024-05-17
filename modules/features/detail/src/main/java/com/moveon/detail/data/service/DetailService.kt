package com.moveon.detail.data.service

import com.moveon.core.ResponseResult
import com.moveon.detail.data.response.DetailMainResponse
import com.moveon.network.network.data.NetworkError
import retrofit2.http.GET
import retrofit2.http.Query

interface DetailService {

    @GET("get?output_language=en")
    suspend fun fetchDetail(
        @Query("imdb_id") imdbId: String?,
        @Query("tmdb_id") tmdbId: Int?
    ): ResponseResult<DetailMainResponse, NetworkError>
}