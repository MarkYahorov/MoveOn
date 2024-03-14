package com.moveon.network.di.modules

import com.moveon.network.di.qualifaers.BaseRetrofitBuilder
import com.moveon.network.di.qualifaers.BaseRetrofitProvider
import com.moveon.network.di.qualifaers.CountriesRetrofitBuilder
import com.moveon.network.di.qualifaers.CountriesRetrofitProvider
import com.moveon.network.network.provider.RetrofitServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object NetworkRetrofitModule {

    @Provides
    @BaseRetrofitProvider
    fun provideRetrofitNetwork(
        @BaseRetrofitBuilder builder: Retrofit.Builder
    ): RetrofitServiceProvider {
        return RetrofitServiceProvider(builder.build())
    }

    @Provides
    @CountriesRetrofitProvider
    fun provideCountriesRetrofitNetwork(
        @CountriesRetrofitBuilder builder: Retrofit.Builder
    ): RetrofitServiceProvider {
        return RetrofitServiceProvider(builder.build())
    }
}