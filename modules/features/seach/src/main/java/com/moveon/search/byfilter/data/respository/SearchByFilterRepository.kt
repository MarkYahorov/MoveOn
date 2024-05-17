package com.moveon.search.byfilter.data.respository

import com.moveon.core.ResponseResult
import com.moveon.network.network.data.CountriesResponse
import com.moveon.network.network.data.NetworkError
import com.moveon.pagination.repository.PaginationRepository
import com.moveon.search.byfilter.data.data.request.SearchByFilterRequest
import com.moveon.search.byfilter.presentation.data.SearchByFilterItemPresentation

interface SearchByFilterRepository :
    PaginationRepository<SearchByFilterRequest, SearchByFilterItemPresentation, NetworkError> {

    suspend fun getCountries(): ResponseResult<CountriesResponse, NetworkError>
}