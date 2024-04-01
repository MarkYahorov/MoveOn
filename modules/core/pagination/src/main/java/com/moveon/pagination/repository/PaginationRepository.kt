package com.moveon.pagination.repository

import com.moveon.core.ResponseError
import com.moveon.core.ResponseResult
import com.moveon.core.repository.Repository
import com.moveon.pagination.data.PagingPageResponse

interface PaginationRepository<Request, Value : Any, Err : ResponseError>: Repository {

    suspend fun load(
        request: Request?,
        nextPage: String
    ): ResponseResult<PagingPageResponse<Value>, Err>
}
