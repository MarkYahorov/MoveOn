package com.moveon.network.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import okhttp3.Interceptor
import okhttp3.OkHttpClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkClientModule {

    @Provides
    fun provideOkHttpClient(
        interceptors: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .callTimeout(40L, TimeUnit.SECONDS)
            .connectTimeout(40L, TimeUnit.SECONDS)
            .readTimeout(40L, TimeUnit.SECONDS)
            .writeTimeout(40L, TimeUnit.SECONDS)
            .retryOnConnectionFailure(true)
            .addInterceptor(interceptors)
            .build()
    }
}