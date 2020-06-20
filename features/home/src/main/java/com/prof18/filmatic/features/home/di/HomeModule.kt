/*
 * Copyright 2020 Marco Gomiero
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.prof18.filmatic.features.home.di

import android.content.Context
import coil.ImageLoader
import coil.ImageLoaderBuilder
import com.prof18.filmatic.features.home.data.HomeRepositoryImpl
import com.prof18.filmatic.features.home.data.remote.HomeRemoteDataSource
import com.prof18.filmatic.features.home.domain.HomeRepository
import com.prof18.filmatic.features.home.remote.HomeRemoteDataSourceImpl
import com.prof18.filmatic.features.home.remote.api.HomeService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
@InstallIn(ActivityComponent::class)
class HomeModule {

    @Provides
    fun provideImageLoader(
        @ApplicationContext context: Context,
        okHttpClient: OkHttpClient
    ): ImageLoader {
        val imageLoaderBuilder = ImageLoaderBuilder(context)
        imageLoaderBuilder.okHttpClient(okHttpClient)
        return imageLoaderBuilder.build()
    }

    @Provides
    fun provideHomePresenter(homeRepository: HomeRepositoryImpl): HomeRepository {
        return homeRepository
    }

    @Provides
    fun provideRemoteDataSource(homeRemoteDataSourceImpl: HomeRemoteDataSourceImpl): HomeRemoteDataSource {
        return homeRemoteDataSourceImpl
    }

    @Provides
    fun provideHomeService(
        client: OkHttpClient
    ): HomeService {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(HomeService::class.java)
    }
}