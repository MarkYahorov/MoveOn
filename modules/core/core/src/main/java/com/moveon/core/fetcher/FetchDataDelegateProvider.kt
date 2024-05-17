package com.moveon.core.fetcher

import androidx.lifecycle.viewModelScope
import com.moveon.core.Error
import com.moveon.core.ResponseError
import com.moveon.core.ResponseResult
import com.moveon.core.Success
import com.moveon.core.repository.Repository
import com.moveon.core.viewmodel.CoreViewModel
import kotlinx.coroutines.launch

fun <S : Any?, Input, Err : ResponseError, R : Repository> CoreViewModel.fetchData(
    repository: R,
    callFunction: suspend (R, Input) -> ResponseResult<S, Err>,
    input: Input? = null,
    onSuccess: (new: S?) -> Unit = { },
    onError: ((Err?) -> Unit)? = null,
): FetchDataDelegateProvider<CoreViewModel, S, Input, Err, R> {
    return FetchDataDelegateProvider(
        repository,
        callFunction = callFunction,
        input = input,
        viewModel = this,
        onSuccess = onSuccess,
        onError = onError
    )
}


fun <S : Any?, Err : ResponseError, R : Repository> CoreViewModel.fetchData(
    repository: R,
    callFunction: suspend (R) -> ResponseResult<S, Err>,
    onSuccess: (new: S?) -> Unit = { },
    onError: ((Err?) -> Unit)? = null,
): FetchDataDelegateProvider<CoreViewModel, S, Any, Err, R> {
    return FetchDataDelegateProvider(
        repository = repository,
        input = Any(),
        callFunction = { p: R, _: Any -> callFunction(p) },
        onSuccess = onSuccess,
        onError = onError,
        viewModel = this
    )
}

class FetchDataDelegateProvider<VM : CoreViewModel, S, Input, Err : ResponseError, R : Repository>(
    internal val repository: R,
    internal val callFunction: suspend (R, Input) -> ResponseResult<S, Err>,
    internal val input: Input?,
    internal val viewModel: VM,
    internal val onSuccess: (new: S?) -> Unit = { },
    internal val onError: ((Err?) -> Unit)? = null,
) : Fetcher<Input> {

    private val fetchFun: (Input) -> Unit = {
        viewModel.viewModelScope.launch {
            when (val result = callFunction.invoke(repository, it)) {
                is Error -> {
                    onError?.let { onError.invoke(result.error) }
                }

                is Success -> {
                    onSuccess.invoke(result.value)
                }
            }
        }
    }

    override fun fetch() {
        input?.let {
            fetchFun.invoke(it)
        }
    }

    override fun fetch(input: Input) {
        fetchFun.invoke(input)
    }
}