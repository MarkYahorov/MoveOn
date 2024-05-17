package com.moveon.search.byfilter.presentation

import com.moveon.core.ResponseResult
import com.moveon.core.mapOk
import com.moveon.network.network.CountriesService
import com.moveon.network.network.data.CountriesResponse
import com.moveon.network.network.data.NetworkError
import com.moveon.pagination.data.PagingPageResponse
import com.moveon.search.byfilter.data.data.request.SearchByFilterRequest
import com.moveon.search.byfilter.data.respository.SearchByFilterRepository
import com.moveon.search.byfilter.data.service.SearchByFilterService
import com.moveon.search.byfilter.presentation.data.SearchByFilterItemPresentation
import com.moveon.search.byfilter.presentation.mapper.SearchByFilterMapper
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchFilterRepositoryImpl @Inject constructor(
    private val service: SearchByFilterService,
    private val countriesService: CountriesService,
    private val mapper: SearchByFilterMapper
) : SearchByFilterRepository {
    override suspend fun load(
        request: SearchByFilterRequest?,
        nextPage: String
    ): ResponseResult<PagingPageResponse<SearchByFilterItemPresentation>, NetworkError> {
        require(request != null)

        return withContext(Dispatchers.IO) {
            service.searchByFilters(
                request.service,
                request.country,
                request.keyword,
                nextPage,
                request.showType
            )
        }.mapOk {
            mapper.map(it)
        }
    }

    override suspend fun getCountries(): ResponseResult<CountriesResponse, NetworkError> {
        return countriesService.getCountries()
    }
}