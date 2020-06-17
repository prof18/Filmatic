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
import com.prof18.filmatic.core.architecture.ViewState
import com.prof18.filmatic.features.home.DataFactory
import com.prof18.filmatic.features.home.FakeErrorHomeRepository
import com.prof18.filmatic.features.home.FakeSuccessHomeRepository
import com.prof18.filmatic.features.home.domain.entities.Genre
import com.prof18.filmatic.features.home.domain.usecases.GetGenresUseCase
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import com.prof18.filmatic.features.home.presentation.explore.model.ExploreItem
import com.prof18.filmatic.libraries.testshared.CoroutinesTestRule
import com.prof18.filmatic.libraries.testshared.getOrAwaitValue
import com.prof18.filmatic.libraries.testshared.provideFakeCoroutinesDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
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
        CoroutinesTestRule()

    private var successHomeRepository = FakeSuccessHomeRepository()
    private var errorHomeRepository = FakeErrorHomeRepository()

    private lateinit var getPopularMoviesUseCase: GetPopularMoviesUseCase
    private lateinit var getGenresUseCase: GetGenresUseCase

    private val testCoroutineDispatcher = TestCoroutineDispatcher()
    private var viewModel: HomeViewModel? = null

    @After
    fun tearDown() {
        viewModel = null
        testCoroutineDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun fetchPopularMoviesShouldEmitLoadingAndThenSuccess() =
        testCoroutineDispatcher.runBlockingTest {

            val movie = DataFactory.getMovie()

            successHomeRepository.movies = listOf(movie)
            getPopularMoviesUseCase = GetPopularMoviesUseCase(successHomeRepository)
            getGenresUseCase = GetGenresUseCase(successHomeRepository)

            viewModel = HomeViewModel(
                getPopularMoviesUseCase,
                getGenresUseCase,
                provideFakeCoroutinesDispatcherProvider(
                    testCoroutineDispatcher
                )
            )

            // Pause the dispatcher to observe the loading value
            testCoroutineDispatcher.pauseDispatcher()

            viewModel!!.fetchExploreItems()

            val loadingResult = viewModel!!.exploreState.getOrAwaitValue()
            assertEquals(loadingResult, ViewState.Loading)

            testCoroutineDispatcher.resumeDispatcher()

            val successResult = viewModel!!.exploreState.getOrAwaitValue()

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

            errorHomeRepository.exception = exception
            getPopularMoviesUseCase = GetPopularMoviesUseCase(errorHomeRepository)
            getGenresUseCase = GetGenresUseCase(successHomeRepository)

            viewModel = HomeViewModel(
                getPopularMoviesUseCase,
                getGenresUseCase,
                provideFakeCoroutinesDispatcherProvider(
                    testCoroutineDispatcher
                )
            )

            testCoroutineDispatcher.pauseDispatcher()

            viewModel?.fetchExploreItems()

            val loadingResult = viewModel!!.exploreState.getOrAwaitValue()
            assertEquals(loadingResult, ViewState.Loading)

            testCoroutineDispatcher.resumeDispatcher()

            val errorResult = viewModel!!.exploreState.getOrAwaitValue()
            assertEquals(errorResult, ViewState.Error(exception.localizedMessage ?: ""))
        }

    @Test
    fun `get next movie returns null when no one has backdrop path`() {
        val movie = DataFactory.getMovieWithNoBackdrop()
        val movies = listOf(movie, movie)

        successHomeRepository.movies = movies
        getPopularMoviesUseCase = GetPopularMoviesUseCase(successHomeRepository)
        getGenresUseCase = GetGenresUseCase(successHomeRepository)

        viewModel = HomeViewModel(
            getPopularMoviesUseCase,
            getGenresUseCase,
            provideFakeCoroutinesDispatcherProvider(
                testCoroutineDispatcher
            )
        )

        val randomMovie = viewModel!!.getNextMovieToSee(movies)

        assertNull(randomMovie)
    }

    @Test
    fun `get next movie returns correct data`() {
        val movieWithImage = DataFactory.getMovie()
        val movies = listOf(movieWithImage, movieWithImage)

        successHomeRepository.movies = movies
        getPopularMoviesUseCase = GetPopularMoviesUseCase(successHomeRepository)
        getGenresUseCase = GetGenresUseCase(successHomeRepository)

        viewModel = HomeViewModel(
            getPopularMoviesUseCase,
            getGenresUseCase,
            provideFakeCoroutinesDispatcherProvider(
                testCoroutineDispatcher
            )
        )

        val randomMovie = viewModel!!.getNextMovieToSee(movies)
        assertNotNull(randomMovie)
        assertEquals(randomMovie?.backdropUrl, movieWithImage.backdropUrl)
    }

    @Test
    fun fetchGenresShouldEmitLoadingAndThenSuccess() =
        testCoroutineDispatcher.runBlockingTest {

            val genre = DataFactory.getGenre()

            successHomeRepository.genres = listOf(genre)
            getPopularMoviesUseCase = GetPopularMoviesUseCase(successHomeRepository)
            getGenresUseCase = GetGenresUseCase(successHomeRepository)

            viewModel = HomeViewModel(
                getPopularMoviesUseCase,
                getGenresUseCase,
                provideFakeCoroutinesDispatcherProvider(
                    testCoroutineDispatcher
                )
            )

            // Pause the dispatcher to observe the loading value
            testCoroutineDispatcher.pauseDispatcher()

            viewModel!!.fetchGenres()

            val loadingResult = viewModel!!.discoverState.getOrAwaitValue()
            assertEquals(loadingResult, ViewState.Loading)

            testCoroutineDispatcher.resumeDispatcher()

            val successResult = viewModel!!.discoverState.getOrAwaitValue()

            val successItems = (successResult as ViewState.Success<List<Genre>>).data

            assertEquals(genre.name, successItems.first().name)
            assertEquals(genre.id, successItems.first().id)

        }

    @Test
    fun fetchGenresShouldEmitLoadingAndThenError() =
        testCoroutineDispatcher.runBlockingTest {
            val exception = IOException("No network")

            errorHomeRepository.exception = exception
            getPopularMoviesUseCase = GetPopularMoviesUseCase(errorHomeRepository)
            getGenresUseCase = GetGenresUseCase(errorHomeRepository)

            viewModel = HomeViewModel(
                getPopularMoviesUseCase,
                getGenresUseCase,
                provideFakeCoroutinesDispatcherProvider(
                    testCoroutineDispatcher
                )
            )

            testCoroutineDispatcher.pauseDispatcher()

            viewModel?.fetchGenres()

            val loadingResult = viewModel!!.discoverState.getOrAwaitValue()
            assertEquals(loadingResult, ViewState.Loading)

            testCoroutineDispatcher.resumeDispatcher()

            val errorResult = viewModel!!.discoverState.getOrAwaitValue()
            assertEquals(errorResult, ViewState.Error(exception.localizedMessage ?: ""))
        }

}