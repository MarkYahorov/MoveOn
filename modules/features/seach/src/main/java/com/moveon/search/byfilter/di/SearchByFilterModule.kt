package com.moveon.search.byfilter.di

import com.moveon.network.di.qualifaers.BaseRetrofitProvider
import com.moveon.network.network.provider.RetrofitServiceProvider
import com.moveon.search.byfilter.data.respository.SearchByFilterRepository
import com.moveon.search.byfilter.data.service.SearchByFilterService
import com.moveon.search.byfilter.presentation.SearchFilterRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface SearchByFilterModule {

    companion object {
        @Provides
        fun provideSearchByFilterService(
            @BaseRetrofitProvider retrofitProvider: RetrofitServiceProvider
        ): SearchByFilterService {
            return retrofitProvider.createService(SearchByFilterService::class)
        }
    }

    @Binds
    fun bindSearchByFilterRepository(repository: SearchFilterRepositoryImpl): SearchByFilterRepository
}