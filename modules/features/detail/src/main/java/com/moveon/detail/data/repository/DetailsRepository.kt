package com.moveon.detail.data.repository

import com.moveon.core.ResponseResult
import com.moveon.core.repository.Repository
import com.moveon.detail.data.request.DetailRequest
import com.moveon.detail.presentation.data.DetailPresentation
import com.moveon.network.network.data.NetworkError

interface DetailsRepository : Repository {

    suspend fun fetchDetail(request: DetailRequest): ResponseResult<DetailPresentation, NetworkError>
}