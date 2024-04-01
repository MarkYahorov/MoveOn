package com.moveon.pagination.fetcher

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.moveon.core.ResponseError
import com.moveon.core.viewmodel.CoreViewModel
import com.moveon.pagination.data.PagingPageResponse
import com.moveon.pagination.data.PagingRequest
import com.moveon.pagination.repository.PaginationRepository
import com.moveon.pagination.source.PaginationSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

private const val DEFAULT_PAGE_SIZE = 25
fun <S : Any, Input : PagingRequest, Err : ResponseError, R : PaginationRepository<Input, S, Err>> CoreViewModel.fetchPagingData(
    repository: R,
    input: Input? = null,
    onSuccess: (new: PagingPageResponse<S>?) -> Unit = { },
    onError: (Err?) -> Unit = {},
): PaginationFetcherProvider<CoreViewModel, Input, S, Err> {
    return PaginationFetcherProvider(
        repository,
        input = input,
        viewModel = this,
        onSuccess = onSuccess,
        onError = onError
    )
}

class PaginationFetcherProvider<
        VM : CoreViewModel,
        Input : PagingRequest,
        Value : Any,
        Err : ResponseError>(
    val provider: PaginationRepository<Input, Value, Err>,
    pageSize: Int = DEFAULT_PAGE_SIZE,
    val viewModel: VM,
    val input: Input?,
    onSuccess: (PagingPageResponse<Value>) -> Unit,
    onError: (Err?) -> Unit
) : PaginationFetcher<Input, Value> {

    private val keys = mutableListOf<String>()

    private val paginationSource = PaginationSource(provider, keys, {
        onSuccess.invoke(it)
    }, {
        onError.invoke(it)
    })

    private val pager: Pager<String, Value> by lazy {
        Pager(PagingConfig(pageSize = pageSize), pagingSourceFactory = {
            paginationSource
        })
    }

    private val fetchFun: (Input) -> Flow<PagingData<Value>> = {
        paginationSource.request = it
        pager.flow.cachedIn(viewModel.viewModelScope)
    }


    override fun invalidate() {
        clearKeys()
        paginationSource.invalidate()
    }

    override fun fetch(): Flow<PagingData<Value>> {
        clearKeys()
        return input?.let {
            fetchFun.invoke(it)
        } ?: emptyFlow()
    }

    override fun fetch(input: Input): Flow<PagingData<Value>> {
        clearKeys()
        return fetchFun.invoke(input)
    }

    private fun clearKeys() {
        if (keys.isNotEmpty()) {
            keys.clear()
        }
    }
}