package com.moveon.core

sealed class ResponseResult<T : Any?, Err>(open val value: Any?) {

    companion object
}

data class Success<T, Err>(override val value: T) : ResponseResult<T, Err>(value = value)

data class Error<T, Err>(val error: Err) : ResponseResult<T, Err>(value = error)

fun <S, Err> ResponseResult.Companion.success(value: S): ResponseResult<S, Err> {
    return Success(value = value)
}

fun <S, Err> ResponseResult.Companion.error(throwable: Err): ResponseResult<S, Err> {
    return Error(error = throwable)
}

fun <S, Err, R> ResponseResult<S, Err>.mapOk(map: (S) -> R): ResponseResult<R, Err> {
    return when (this) {
        is Success<S, Err> -> ResponseResult.success(map(value))
        is Error<S, Err> -> ResponseResult.error(error)
    }
}

fun <S, Err, E> ResponseResult<S, Err>.mapErr(map: (Err) -> E): ResponseResult<S, E> {
    return when (this) {
        is Success<S, Err> -> ResponseResult.success(value)
        is Error<S, Err> -> ResponseResult.error(map(error))
    }
}