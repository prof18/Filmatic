package com.prof18.filmatic.features.home.dagger

import com.prof18.filmatic.core.UserPreferenceManager
import com.prof18.filmatic.core.dagger.scope.FeatureScope
import com.prof18.filmatic.features.home.BuildConfig
import com.prof18.filmatic.features.home.data.api.HomeService
import com.prof18.filmatic.features.home.data.popular.PopularRemoteDataSource
import com.prof18.filmatic.features.home.data.popular.PopularRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class HomeModule {

    @Provides
    @FeatureScope
    fun provideHomeService(
        client: OkHttpClient
    ): HomeService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(HomeService::class.java)
    }

    @Provides
    @FeatureScope
    fun providePopularRepository(popularRemoteDataSource: PopularRemoteDataSource): PopularRepository =
        PopularRepository(popularRemoteDataSource)

    @Provides
    @FeatureScope
    fun providePopularRemoteDataSource(
        service: HomeService,
        userPreferenceManager: UserPreferenceManager
    ): PopularRemoteDataSource =
        PopularRemoteDataSource(service, userPreferenceManager)


}