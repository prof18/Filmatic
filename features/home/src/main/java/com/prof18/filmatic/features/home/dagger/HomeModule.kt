package com.prof18.filmatic.features.home.dagger

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.prof18.filmatic.core.dagger.scope.FeatureScope
import com.prof18.filmatic.features.home.BuildConfig
import com.prof18.filmatic.features.home.data.api.HomeService
import com.prof18.filmatic.features.home.data.popular.PopularRemoteDataSource
import com.prof18.filmatic.features.home.data.popular.PopularRepository
import com.prof18.filmatic.features.home.ui.HomeActivity
import com.prof18.filmatic.features.home.ui.HomeViewModel
import com.prof18.filmatic.features.home.ui.HomeViewModelFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class HomeModule(private val activity: HomeActivity) {

    @Provides
    @FeatureScope
    fun provideTmdbService(
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
    fun provideHomeViewModel(factory: HomeViewModelFactory): HomeViewModel =
        ViewModelProviders.of(activity, factory).get(HomeViewModel::class.java)

    @Provides
    @FeatureScope
    fun provideHomeViewModelFactory(popularRepository: PopularRepository): HomeViewModelFactory =
        HomeViewModelFactory(popularRepository)

    @Provides
    @FeatureScope
    fun providePopularRepository(popularRemoteDataSource: PopularRemoteDataSource): PopularRepository =
        PopularRepository(popularRemoteDataSource)

    @Provides
    @FeatureScope
    fun providePopularRemoteDataSource(service: HomeService): PopularRemoteDataSource =
        PopularRemoteDataSource(service)

}