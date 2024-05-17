package com.moveon.detail.di

import com.moveon.detail.data.repository.DetailsRepository
import com.moveon.detail.data.service.DetailService
import com.moveon.detail.presentation.repository.DetailRepositoryImpl
import com.moveon.network.di.qualifaers.BaseRetrofitProvider
import com.moveon.network.network.provider.RetrofitServiceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface DetailModule {

    companion object {
        @Provides
        fun provideDetailService(
            @BaseRetrofitProvider retrofitProvider: RetrofitServiceProvider
        ): DetailService {
            return retrofitProvider.createService(DetailService::class)
        }
    }

    @Binds
    fun bindDetailsRepository(repoImpl: DetailRepositoryImpl): DetailsRepository
}