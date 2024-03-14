package com.moveon.network.network.interceptors

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

private const val API_KEY_HEADER_NAME = "X-RapidAPI-Key"
private const val API_KEY_VALUE = "93e05205b7msh608df996c353b10p1914bcjsn65fea3a5961b"
private const val API_HOST_NAME = "X-RapidAPI-Host"
private const val API_HOST_VALUE = "streaming-availability.p.rapidapi.com"

class HeadersInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder()
            .addHeader(API_KEY_HEADER_NAME, API_KEY_VALUE)
            .addHeader(API_HOST_NAME, API_HOST_VALUE)
            .build()
        return chain.proceed(newRequest)
    }
}