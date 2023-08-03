package com.prof18.filmatic.features.home.di

import com.prof18.filmatic.features.home.HomeContractImpl
import com.prof18.filmatic.features.home.contract.HomeContract
import com.prof18.filmatic.features.home.data.HomeRepositoryImpl
import com.prof18.filmatic.features.home.data.datasource.HomeRemoteDataSource
import com.prof18.filmatic.features.home.data.remote.HomeApiService
import com.prof18.filmatic.features.home.data.remote.HomeRemoteDataSourceImpl
import com.prof18.filmatic.features.home.domain.HomeRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun bindRemoteDataSource(impl: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    abstract fun bindHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    @Binds
    abstract fun bindHomeContract(impl: HomeContractImpl): HomeContract

    companion object {
        @Provides
        @Singleton
        fun provideHomeService(
            client: OkHttpClient,
        ): HomeApiService {
            return Retrofit.Builder()
                .client(client)
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(HomeApiService::class.java)
        }
    }
}
