package com.moveon.pagination.repository

import com.moveon.core.ResponseError
import com.moveon.core.ResponseResult
import com.moveon.pagination.data.PagingPageResponse

interface PaginationProvider<Request, Value : Any, Err : ResponseError> {

    suspend fun load(
        request: Request?,
        nextPage: String
    ): ResponseResult<PagingPageResponse<Value>, Err>
}
