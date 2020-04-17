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

import com.nhaarman.mockitokotlin2.mock
import com.prof18.filmatic.core.architecture.CoroutinesDispatcherProvider
import com.prof18.filmatic.core.dagger.scope.FeatureScope
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import com.prof18.filmatic.libraries.testshared.provideFakeCoroutinesDispatcherProvider
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher

@Module
class TestHomeModule {

    @Provides
    @FeatureScope
    fun provideUseCase(): GetPopularMoviesUseCase {
        return mock()
    }

    @ExperimentalCoroutinesApi
    @Provides
    @FeatureScope
    fun provideCoroutineDispatcher(): CoroutinesDispatcherProvider {
        return provideFakeCoroutinesDispatcherProvider(
            TestCoroutineDispatcher()
        )
    }
}