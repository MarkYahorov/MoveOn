package com.moveon.changes.di

import com.moveon.changes.data.ChangesPaginationRepository
import com.moveon.changes.data.repository.ChangesRepository
import com.moveon.changes.data.service.ChangesService
import com.moveon.network.di.qualifaers.BaseRetrofitProvider
import com.moveon.network.network.provider.RetrofitServiceProvider
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface ChangesModule {

    companion object {
        @Provides
        fun provideChangesService(
            @BaseRetrofitProvider retrofitProvider: RetrofitServiceProvider
        ): ChangesService {
            return retrofitProvider.createService(ChangesService::class)
        }
    }

    @Binds
    fun bindChangesRepository(repoImpl: ChangesPaginationRepository): ChangesRepository
}