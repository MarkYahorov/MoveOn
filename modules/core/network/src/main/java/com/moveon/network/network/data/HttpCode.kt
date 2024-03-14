package com.moveon.network.network.data

import retrofit2.Response

enum class HttpCode(val code: Int) {
    Success(200),
    TooManyRequests(429),
    DefaultError(400);

    companion object {
        fun getHttpCodeFromResponse(response: Response<*>): HttpCode {
            return when (response.code()) {
                200 -> Success
                429 -> TooManyRequests
                else -> DefaultError
            }
        }
    }
}