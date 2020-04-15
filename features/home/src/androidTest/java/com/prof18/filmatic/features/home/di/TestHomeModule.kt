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

import com.prof18.filmatic.core.dagger.scope.FeatureScope
import com.prof18.filmatic.features.home.data.HomeRepositoryImpl
import com.prof18.filmatic.features.home.data.mapper.MovieModelMapper
import com.prof18.filmatic.features.home.data.remote.HomeRemoteDataSource
import com.prof18.filmatic.features.home.domain.HomeRepository
import com.prof18.filmatic.features.home.remote.HomeRemoteDataSourceImpl
import com.prof18.filmatic.features.home.remote.api.HomeService
import com.prof18.filmatic.features.home.remote.mapper.MovieResultMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class TestHomeModule {

    @Provides
    @FeatureScope
    fun provideHomeRepository(
        homeRemoteDataSource: HomeRemoteDataSource,
        mapper: MovieModelMapper
    ): HomeRepository {
        return HomeRepositoryImpl(homeRemoteDataSource, mapper)
    }

    @Provides
    @FeatureScope
    fun provideHomeRemoteDataSource(
        homeService: HomeService,
        mapper: MovieResultMapper
    ): HomeRemoteDataSource {
        return HomeRemoteDataSourceImpl(homeService, mapper)
    }

    @Provides
    @FeatureScope
    fun provideMovieModelMapper(): MovieModelMapper {
        return MovieModelMapper()
    }

    @Provides
    @FeatureScope
    fun provideMovieResultMapper(): MovieResultMapper {
        return MovieResultMapper()
    }

    @Provides
    @FeatureScope
    fun provideHomeService(
        client: OkHttpClient
    ): HomeService {

        // TODO: return a mock of retrofit

        return Retrofit.Builder()
            .client(client)
            .baseUrl("https://FAKE.org/3/")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(HomeService::class.java)
    }

}