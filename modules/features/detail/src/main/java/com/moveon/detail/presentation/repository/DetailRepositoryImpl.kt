package com.moveon.detail.presentation.repository

import com.moveon.core.ResponseResult
import com.moveon.core.mapOk
import com.moveon.detail.data.repository.DetailsRepository
import com.moveon.detail.data.request.DetailRequest
import com.moveon.detail.data.service.DetailService
import com.moveon.detail.presentation.data.DetailPresentation
import com.moveon.detail.presentation.mapper.DetailMapper
import com.moveon.network.network.data.NetworkError
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DetailRepositoryImpl @Inject constructor(
    private val service: DetailService,
    private val detailMapper: DetailMapper
) : DetailsRepository {
    override suspend fun fetchDetail(request: DetailRequest): ResponseResult<DetailPresentation, NetworkError> {
        return withContext(Dispatchers.IO) {
            with(request) {
                service.fetchDetail(imdbId, tmdbId)
            }
        }.mapOk {
            detailMapper.map(it)
        }
    }
}