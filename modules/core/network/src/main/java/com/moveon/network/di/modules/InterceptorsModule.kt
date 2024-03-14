package com.moveon.network.di.modules

import com.moveon.network.network.interceptors.HeadersInterceptor
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor

@Module
@InstallIn(SingletonComponent::class)
interface InterceptorsModule {

    @Binds
    fun bindHeaderInterceptor(interceptor: HeadersInterceptor): Interceptor
}