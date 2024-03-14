package com.moveon.network.di.modules

import com.moveon.network.di.qualifaers.CountriesRetrofitProvider
import com.moveon.network.network.CountriesService
import com.moveon.network.network.provider.RetrofitServiceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {

    @Provides
    fun provideCountriesService(
        @CountriesRetrofitProvider retrofitProvider: RetrofitServiceProvider
    ): CountriesService {
        return retrofitProvider.createService(CountriesService::class)
    }
}