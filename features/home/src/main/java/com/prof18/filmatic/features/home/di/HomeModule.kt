package com.prof18.filmatic.features.home.di

import com.m2f.archer.crud.GetRepository
import com.prof18.filmatic.core.net.ConnectivityChecker
import com.prof18.filmatic.features.home.data.remote.HomeApiService
import com.prof18.filmatic.features.home.data.remote.getHomeRepository
import com.prof18.filmatic.features.home.domain.entities.Movie
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
internal class HomeModule {

    @Provides
    @Singleton
    fun providesHomeDataSource(
        homeService: HomeApiService,
        connectivityChecker: ConnectivityChecker,
    ): @JvmSuppressWildcards GetRepository<Unit, List<Movie>> =
        getHomeRepository(homeService, connectivityChecker)

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
