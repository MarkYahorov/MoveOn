package com.moveon.network.network.provider

import kotlin.reflect.KClass
import retrofit2.Retrofit

class RetrofitServiceProvider(private val retrofit: Retrofit) {

    fun <T : Any> createService(service: KClass<T>): T {
        return retrofit.create(service.java)
    }
}