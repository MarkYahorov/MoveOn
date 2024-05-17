package com.moveon.search.byfilter.data.service

import com.moveon.core.ResponseResult
import com.moveon.network.network.data.NetworkError
import com.moveon.pagination.data.PagingPageResponse
import com.moveon.search.byfilter.data.data.request.ShowTypeRequest
import com.moveon.search.byfilter.data.data.response.SearchFilterItemResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchByFilterService {

    @GET("search/filters")
    suspend fun searchByFilters(
        @Query("service") service: String,
        @Query("country") country: String,
        @Query("keyword") keyword: String?,
        @Query("cursor") cursor: String?,
        @Query("showType") showType: ShowTypeRequest,
    ): ResponseResult<PagingPageResponse<SearchFilterItemResponse>, NetworkError>
}