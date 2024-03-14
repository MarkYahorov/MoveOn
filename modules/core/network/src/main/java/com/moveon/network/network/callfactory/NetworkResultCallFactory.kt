package com.moveon.network.network.callfactory

import com.moveon.core.ResponseResult
import com.moveon.network.network.call.NetworkResultCall
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit

class NetworkResultCallFactory : CallAdapter.Factory() {
    override fun get(
        returnType: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ): CallAdapter<*, *>? {
        if (getRawType(returnType) != Call::class.java) {
            return null
        }
        check(returnType is ParameterizedType) {
            ("returnType should be ParameterizedType to continue work")
        }
        val currentType = getParameterUpperBound(0, returnType)
        if (getRawType(currentType) != ResponseResult::class.java) {
            return null
        }
        check(currentType is ParameterizedType) {
            ("currentType should be ParameterizedType to continue work with self response class")
        }
        val resultType = getParameterUpperBound(0, currentType)

        return object : CallAdapter<Any, NetworkResultCall<Any>> {
            override fun responseType(): Type = resultType
            override fun adapt(call: Call<Any>): NetworkResultCall<Any> {
                return NetworkResultCall(call)
            }
        }
    }
}