package com.moveon.pagination.fetcher

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface PaginationFetcher<Input, Value : Any> {

    fun invalidate()

    fun fetch(): Flow<PagingData<Value>>

    fun fetch(input: Input): Flow<PagingData<Value>>
}