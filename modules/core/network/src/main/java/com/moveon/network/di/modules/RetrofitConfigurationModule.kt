package com.moveon.network.di.modules

import com.moveon.network.di.qualifaers.BaseGsonConvertor
import com.moveon.network.di.qualifaers.BaseRetrofitBuilder
import com.moveon.network.di.qualifaers.CountriesGsonConvertor
import com.moveon.network.di.qualifaers.CountriesRetrofitBuilder
import com.moveon.network.network.callfactory.NetworkResultCallFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://streaming-availability.p.rapidapi.com/"

@Module
@InstallIn(SingletonComponent::class)
object RetrofitConfigurationModule {

    @BaseRetrofitBuilder
    @Provides
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
        @BaseGsonConvertor gsonConverterFactory: GsonConverterFactory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(NetworkResultCallFactory())
            .client(okHttpClient)
            .baseUrl(BASE_URL)

    }

    @CountriesRetrofitBuilder
    @Provides
    fun provideCountriesRetrofitBuilder(
        okHttpClient: OkHttpClient,
        @CountriesGsonConvertor gsonConverterFactory: GsonConverterFactory
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(NetworkResultCallFactory())
            .client(okHttpClient)
            .baseUrl(BASE_URL)
    }
}