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

package com.prof18.filmatic.features.home.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.core.architecture.ViewState
import com.prof18.filmatic.features.home.CoroutinesTestRule
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import com.prof18.filmatic.features.home.getOrAwaitValue
import com.prof18.filmatic.features.home.provideFakeCoroutinesDispatcherProvider
import com.prof18.filmatic.features.home.utils.DataFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var coroutinesTestRule = CoroutinesTestRule()

    private val popularMoviesUseCase = mock<GetPopularMoviesUseCase>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val viewModel = HomeViewModel(popularMoviesUseCase, provideFakeCoroutinesDispatcherProvider(testCoroutineDispatcher))

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun fetchPopularMoviesShouldEmitLoadingAndThenSuccess() = testCoroutineDispatcher.runBlockingTest {
        val movie = DataFactory.getMovie()
        stubGetPopularMovieUseCase(listOf(movie))

        // Pause the dispatcher to observe the loading value
        testCoroutineDispatcher.pauseDispatcher()

        viewModel.fetchPopularMovies()

        val loadingResult = viewModel.exploreState.getOrAwaitValue()
        assertEquals(loadingResult, ViewState.Loading)

        testCoroutineDispatcher.resumeDispatcher()

        val successResult = viewModel.exploreState.getOrAwaitValue()
        assertEquals(successResult, ViewState.Success(listOf(movie)))
    }


    @Test
    fun fetchPopularMoviesShouldEmitLoadingAndThenError() = testCoroutineDispatcher.runBlockingTest {
        val exception = IOException("No network")
        stubGetPopularMovieUseCaseWithError(exception)

        testCoroutineDispatcher.pauseDispatcher()

        viewModel.fetchPopularMovies()

        val loadingResult = viewModel.exploreState.getOrAwaitValue()
        assertEquals(loadingResult, ViewState.Loading)

        testCoroutineDispatcher.resumeDispatcher()

        val errorResult = viewModel.exploreState.getOrAwaitValue()
        assertEquals(errorResult, ViewState.Error(exception.localizedMessage ?: ""))
    }


    private fun stubGetPopularMovieUseCase(list: List<Movie>) = runBlocking {
        whenever(popularMoviesUseCase.execute())
            .thenReturn(Result.Success(list))
    }

    private fun stubGetPopularMovieUseCaseWithError(exception: Exception) = runBlocking {
        whenever(popularMoviesUseCase.execute())
            .thenReturn(Result.Error(exception))
    }
}

