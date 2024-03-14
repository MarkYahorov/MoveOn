package com.moveon.changes.data

import com.moveon.changes.data.data.request.ChangesRequest
import com.moveon.changes.data.repository.ChangesRepository
import com.moveon.changes.data.service.ChangesService
import com.moveon.changes.presentation.mappers.ChangesMapper
import com.moveon.core.ResponseResult
import com.moveon.core.mapOk
import com.moveon.network.network.CountriesService
import com.moveon.network.network.data.CountriesResponse
import com.moveon.network.network.data.NetworkError
import com.moveon.pagination.data.PagingPageResponse
import com.moveon.ui_core.data.ChangesItemPresentation
import javax.inject.Inject

class ChangesPaginationRepository @Inject constructor(
    private val changesService: ChangesService,
    private val countriesService: CountriesService,
    private val changesMapper: ChangesMapper
) : ChangesRepository {

    override suspend fun load(
        request: ChangesRequest?,
        nextPage: String
    ): ResponseResult<PagingPageResponse<ChangesItemPresentation>, NetworkError> {
        require(request != null) {
            "request is null"
        }

        return with(request) {
            changesService.getChanges(
                changeType = changeType,
                service = service,
                country = country,
                targetType = targetType,
                pageKey = nextPage
            )
        }.mapOk {
            changesMapper.map(it)
        }
    }

    override suspend fun getCountries(): ResponseResult<CountriesResponse, NetworkError> {
        return countriesService.getCountries()
    }
}