package com.moveon.pagination.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.moveon.core.Error
import com.moveon.core.ResponseError
import com.moveon.core.Success
import com.moveon.pagination.data.PagingPageResponse
import com.moveon.pagination.data.PagingRequest
import com.moveon.pagination.repository.PaginationProvider

class PaginationSource<Request : PagingRequest, Value : Any, Err : ResponseError>(
    private val repository: PaginationProvider<Request, Value, Err>,
    private val keys: MutableList<String> = mutableListOf(),
    var onSuccessFetchData: (PagingPageResponse<Value>) -> Unit,
    var onError: (Err) -> Unit
) : PagingSource<String, Value>() {

    var request: Request? = null

    override suspend fun load(params: LoadParams<String>): LoadResult<String, Value> {
        val key = params.key ?: ""
        keys.add(key)
        return when (val response = repository.load(request, key)) {
            is Success -> {
                val successResponse = response.value
                onSuccessFetchData.invoke(successResponse)
                val nextKey = if (successResponse.hasKey) successResponse.nextKey else null
                LoadResult.Page(
                    successResponse.data,
                    getPrevKey(key),
                    nextKey
                )
            }

            is Error -> {
                onError(response.error)
                LoadResult.Error(Throwable(response.error.title))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<String, Value>): String? {
        return state.anchorPosition?.let { state.pages[it].nextKey }
    }

    private fun getPrevKey(currentKey: String): String? {
        return if (keys.isEmpty() || currentKey == keys.first()) {
            null
        } else keys[keys.indexOf(currentKey) - 1]
    }
}