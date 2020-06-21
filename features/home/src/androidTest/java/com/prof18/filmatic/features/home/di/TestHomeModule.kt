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

import coil.ImageLoader
import coil.annotation.ExperimentalCoilApi
import com.nhaarman.mockitokotlin2.mock
import com.prof18.filmatic.core.architecture.CoroutineDispatcherProvider
import com.prof18.filmatic.features.home.data.HomeRepositoryImpl
import com.prof18.filmatic.features.home.data.remote.HomeRemoteDataSource
import com.prof18.filmatic.features.home.domain.HomeRepository
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import com.prof18.filmatic.features.home.remote.HomeRemoteDataSourceImpl
import com.prof18.filmatic.features.home.remote.api.HomeService
import com.prof18.filmatic.libraries.testshared.fakeImageLoader
import com.prof18.filmatic.libraries.testshared.provideFakeCoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@InstallIn(ApplicationComponent::class)
@ExperimentalCoilApi
@Module
class TestHomeModule {

    @Provides
    fun provideImageLoader(): ImageLoader {
        return fakeImageLoader
    }

    @Provides
    fun provideUseCase(repository: HomeRepository): GetPopularMoviesUseCase {
        return GetPopularMoviesUseCase(repository)
    }

    @ExperimentalCoroutinesApi
    @Provides
    fun provideCoroutineDispatcher(): CoroutineDispatcherProvider {
        return provideFakeCoroutinesDispatcherProvider(
            TestCoroutineDispatcher()
        )
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
    @Singleton
    fun provideHomeService(): HomeService {
        return mock()
    }
}
