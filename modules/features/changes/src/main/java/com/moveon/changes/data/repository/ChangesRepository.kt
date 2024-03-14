package com.moveon.changes.data.repository

import com.moveon.changes.data.data.request.ChangesRequest
import com.moveon.core.ResponseResult
import com.moveon.core.repository.Repository
import com.moveon.network.network.data.CountriesResponse
import com.moveon.network.network.data.NetworkError
import com.moveon.pagination.repository.PaginationProvider
import com.moveon.ui_core.data.ChangesItemPresentation

interface ChangesRepository :
    PaginationProvider<ChangesRequest, ChangesItemPresentation, NetworkError>, Repository {

    suspend fun getCountries(): ResponseResult<CountriesResponse, NetworkError>
}