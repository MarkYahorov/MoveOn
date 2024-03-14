package com.moveon.changes.data.service

import com.moveon.changes.data.data.request.ChangeType
import com.moveon.changes.data.data.request.TargetType
import com.moveon.changes.data.data.response.ChangeItemResponse
import com.moveon.core.ResponseResult
import com.moveon.network.network.data.NetworkError
import com.moveon.pagination.data.PagingPageResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ChangesService {

    @GET("changes")
    suspend fun getChanges(
        @Query("change_type") changeType: ChangeType,
        @Query("services") service: String,
        @Query("country") country: String,
        @Query("target_type") targetType: TargetType,
        @Query("cursor") pageKey: String
    ): ResponseResult<PagingPageResponse<ChangeItemResponse>, NetworkError>
}