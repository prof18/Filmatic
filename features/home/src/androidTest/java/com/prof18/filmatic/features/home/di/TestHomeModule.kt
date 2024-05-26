package com.prof18.filmatic.features.home.di

import com.prof18.filmatic.features.home.data.datasource.HomeRemoteDataSource
import com.prof18.filmatic.features.home.data.remote.HomeApiService
import com.prof18.filmatic.features.home.data.remote.HomeRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [HomeModule::class],
)
abstract class TestHomeModule {

    @Binds
    abstract fun provideRemoteDataSource(impl: HomeRemoteDataSourceImpl): HomeRemoteDataSource

    @Binds
    abstract fun provideHomeRepository(impl: HomeRepositoryImpl): HomeRepository

    companion object {

        @Provides
        @Singleton
        fun provideMockWebServer(): MockWebServer {
            return MockWebServer()
        }

        @Provides
        @Singleton
        fun provideHomeService(
            client: OkHttpClient,
            mockWebServer: MockWebServer,
        ): HomeApiService {
            return Retrofit.Builder()
                .client(client)
                .baseUrl(mockWebServer.url("/"))
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(HomeApiService::class.java)
        }
    }
}
