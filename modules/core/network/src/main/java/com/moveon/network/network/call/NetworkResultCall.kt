package com.moveon.network.network.call

import com.moveon.core.ResponseResult
import com.moveon.core.error
import com.moveon.core.success
import com.moveon.network.network.data.HttpCode
import com.moveon.network.network.data.NetworkError
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkResultCall<S : Any>(private val original: Call<S>) :
    Call<ResponseResult<S, NetworkError>> {

    override fun enqueue(callback: Callback<ResponseResult<S, NetworkError>>) {
        original.enqueue(
            object : Callback<S> {
                override fun onResponse(call: Call<S>, response: Response<S>) {
                    val code = HttpCode.getHttpCodeFromResponse(response)
                    if (response.isSuccessful) {
                        if (code == HttpCode.TooManyRequests) {
                            callback.onResponse(
                                this@NetworkResultCall,
                                Response.success(
                                    ResponseResult.error(
                                        NetworkError(
                                            "To many requests",
                                            "u can try later",
                                            code
                                        )
                                    )
                                )
                            )
                        } else {
                            when (val body = response.body()) {
                                null -> callback.onResponse(
                                    this@NetworkResultCall,
                                    Response.success(
                                        ResponseResult.error(
                                            NetworkError(
                                                "oops its bad",
                                                "no answer",
                                                code
                                            )
                                        )
                                    )
                                )

                                else -> {
                                    callback.onResponse(
                                        this@NetworkResultCall,
                                        Response.success(
                                            ResponseResult.success(body)
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<S>, t: Throwable) {

                    val error = NetworkError(
                        title = "Some error",
                        detail = t.message,
                        httpCode = HttpCode.DefaultError
                    )
                    callback.onResponse(
                        this@NetworkResultCall,
                        Response.success(ResponseResult.error(error))
                    )
                }

            }
        )
    }

    override fun clone(): Call<ResponseResult<S, NetworkError>> {
        return NetworkResultCall(original.clone())
    }

    override fun execute(): Response<ResponseResult<S, NetworkError>> {
        throw UnsupportedOperationException("NetworkResultCall doesn't support execute")
    }

    override fun isExecuted(): Boolean = original.isExecuted

    override fun cancel() = original.cancel()

    override fun isCanceled(): Boolean = original.isCanceled

    override fun request(): Request = original.request()

    override fun timeout(): Timeout = original.timeout()
}