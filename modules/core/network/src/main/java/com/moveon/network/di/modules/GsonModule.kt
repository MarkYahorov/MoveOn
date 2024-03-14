package com.moveon.network.di.modules

import com.google.gson.GsonBuilder
import com.moveon.network.di.qualifaers.BaseGsonConvertor
import com.moveon.network.di.qualifaers.CountriesGsonConvertor
import com.moveon.network.network.data.CountriesResponse
import com.moveon.network.network.deserializers.CountryResultDeserializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object GsonModule {

    @Provides
    fun provideGsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }

    @BaseGsonConvertor
    @Provides
    fun provideGsonConverterFactory(gsonBuilder: GsonBuilder): GsonConverterFactory {
        return GsonConverterFactory.create(gsonBuilder.create())
    }

    @CountriesGsonConvertor
    @Provides
    fun provideGsonCountriesConverterFactory(gsonBuilder: GsonBuilder): GsonConverterFactory {
        return GsonConverterFactory.create(
            gsonBuilder.registerTypeAdapter(
                CountriesResponse::class.java,
                CountryResultDeserializer()
            ).create()
        )
    }
}