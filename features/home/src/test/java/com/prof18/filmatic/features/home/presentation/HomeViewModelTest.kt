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
import com.prof18.filmatic.libraries.testshared.CoroutinesTestRule
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import com.prof18.filmatic.libraries.testshared.getOrAwaitValue
import com.prof18.filmatic.features.home.presentation.explore.model.ExploreItem
import com.prof18.filmatic.libraries.testshared.provideFakeCoroutinesDispatcherProvider
import com.prof18.filmatic.features.home.DataFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
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
    var coroutinesTestRule =
        com.prof18.filmatic.libraries.testshared.CoroutinesTestRule()

    private val popularMoviesUseCase = mock<GetPopularMoviesUseCase>()
    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private val viewModel = HomeViewModel(
        popularMoviesUseCase,
        com.prof18.filmatic.libraries.testshared.provideFakeCoroutinesDispatcherProvider(
            testCoroutineDispatcher
        )
    )

    @After
    fun tearDown() {
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun fetchPopularMoviesShouldEmitLoadingAndThenSuccess() =
        testCoroutineDispatcher.runBlockingTest {
            val movie = DataFactory.getMovie()
            stubGetPopularMovieUseCase(listOf(movie))

            // Pause the dispatcher to observe the loading value
            testCoroutineDispatcher.pauseDispatcher()

            viewModel.fetchExploreItems()

            val loadingResult = viewModel.exploreState.getOrAwaitValue()
            assertEquals(loadingResult, ViewState.Loading)

            testCoroutineDispatcher.resumeDispatcher()

            val successResult = viewModel.exploreState.getOrAwaitValue()

            val successItems = (successResult as ViewState.Success<List<ExploreItem>>).data
            val collection = successItems
                .first {
                    (it is ExploreItem.TrendingCollection)
                }
            val item = (collection as ExploreItem.TrendingCollection).data.items[0]
            assertEquals(item.id, movie.id)
            assertEquals(item.title, movie.title)
            assertEquals(item.imageUrl, movie.backdropUrl)
        }


    @Test
    fun fetchPopularMoviesShouldEmitLoadingAndThenError() =
        testCoroutineDispatcher.runBlockingTest {
            val exception = IOException("No network")
            stubGetPopularMovieUseCaseWithError(exception)

            testCoroutineDispatcher.pauseDispatcher()

            viewModel.fetchExploreItems()

            val loadingResult = viewModel.exploreState.getOrAwaitValue()
            assertEquals(loadingResult, ViewState.Loading)

            testCoroutineDispatcher.resumeDispatcher()

            val errorResult = viewModel.exploreState.getOrAwaitValue()
            assertEquals(errorResult, ViewState.Error(exception.localizedMessage ?: ""))
        }

    @Test
    fun `get next movie returns null when no one has backdrop path`() {
        val movie = DataFactory.getMovieWithNoBackdrop()
        val movies = listOf(movie, movie)

        val randomMovie = viewModel.getNextMovieToSee(movies)

        assertNull(randomMovie)
    }

    @Test
    fun `get next movie returns correct data`() {
        val movieWithImage = DataFactory.getMovie()
        val movies = listOf(movieWithImage, movieWithImage)

        val randomMovie = viewModel.getNextMovieToSee(movies)
        assertNotNull(randomMovie)
        assertEquals(randomMovie?.backdropUrl, movieWithImage.backdropUrl)
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

