package com.prof18.filmatic.features.home.presentation

import app.cash.turbine.test
import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.core.architecture.UIState
import com.prof18.filmatic.core.error.NetworkError
import com.prof18.filmatic.features.home.DataFactory
import com.prof18.filmatic.features.home.data.testutils.generateHomeRepositoryWithFakeRemoteDataSource
import com.prof18.filmatic.features.home.domain.usecases.GetPopularMoviesUseCase
import com.prof18.filmatic.features.home.presentation.state.HomeListItem
import com.prof18.filmatic.libraries.testshared.MainCoroutineRule
import com.prof18.filmatic.libraries.testshared.testCoroutineDispatcherProvider
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {

    @get:Rule
    val coroutineRule = MainCoroutineRule(testCoroutineDispatcherProvider.main)

    @Test
    fun `homeState emit success state`() = runTest {
        val sut = HomeViewModel(
            popularMoviesUseCase = GetPopularMoviesUseCase(
                homeRepository = generateHomeRepositoryWithFakeRemoteDataSource(
                    popularMovieResponse = DataResult.Success(DataFactory.popularMoviesDTO),
                ),
            ),
        )

        sut.homeState.test {
            assertTrue(awaitItem() is UIState.Loading)

            sut.getHomeState()

            assertTrue(awaitItem() is UIState.Success)
        }
    }

    @Test
    fun `getHomeState generates correct state`() = runTest {
        val sut = HomeViewModel(
            popularMoviesUseCase = GetPopularMoviesUseCase(
                homeRepository = generateHomeRepositoryWithFakeRemoteDataSource(
                    popularMovieResponse = DataResult.Success(DataFactory.popularMoviesDTO),
                ),
            ),
        )

        sut.homeState.test {
            assertTrue(awaitItem() is UIState.Loading)

            sut.getHomeState()

            val state = awaitItem() as UIState.Success
            val homeItems = state.data

            // Header
            assertTrue(homeItems[0] is HomeListItem.Header)
            // Trending Collection -> size 3
            assertTrue(homeItems[1] is HomeListItem.TrendingCollection)
            assertEquals(3, (homeItems[1] as HomeListItem.TrendingCollection).data.size)

            // Header
            assertTrue(homeItems[2] is HomeListItem.Header)
            // Movie Big Card
            assertTrue(homeItems[3] is HomeListItem.MovieBigCard)
        }
    }

    @Test
    fun `homeState emit NoData state if movie list is empty`() = runTest {
        val sut = HomeViewModel(
            popularMoviesUseCase = GetPopularMoviesUseCase(
                homeRepository = generateHomeRepositoryWithFakeRemoteDataSource(
                    popularMovieResponse = DataResult.Success(DataFactory.emptyPopularMovieDTO),
                ),
            ),
        )

        sut.homeState.test {
            assertTrue(awaitItem() is UIState.Loading)

            sut.getHomeState()

            assertTrue(awaitItem() is UIState.NoData)
        }
    }

    @Test
    fun `homeState emit error state`() = runTest {
        val sut = HomeViewModel(
            popularMoviesUseCase = GetPopularMoviesUseCase(
                homeRepository = generateHomeRepositoryWithFakeRemoteDataSource(
                    popularMovieResponse = DataResult.Error(NetworkError.NotFound),
                ),
            ),
        )

        sut.homeState.test {
            assertTrue(awaitItem() is UIState.Loading)

            sut.getHomeState()

            assertTrue(awaitItem() is UIState.Error)
        }
    }

    @Test
    fun `movieDetailState emit NoData when movie is not found`() = runTest {
        val sut = HomeViewModel(
            popularMoviesUseCase = GetPopularMoviesUseCase(
                homeRepository = generateHomeRepositoryWithFakeRemoteDataSource(
                    popularMovieResponse = DataResult.Success(DataFactory.emptyPopularMovieDTO),
                ),
            ),
        )

        sut.movieDetailState.test {
            assertTrue(awaitItem() is UIState.Loading)

            sut.getHomeState()
            sut.getMovie(movieId = -1)

            assertTrue(awaitItem() is UIState.NoData)
        }
    }

    @Test
    fun `movieDetailState emit success`() = runTest {
        val sut = HomeViewModel(
            popularMoviesUseCase = GetPopularMoviesUseCase(
                homeRepository = generateHomeRepositoryWithFakeRemoteDataSource(
                    popularMovieResponse = DataResult.Success(DataFactory.popularMoviesDTO),
                ),
            ),
        )

        sut.getHomeState()

        advanceUntilIdle()

        sut.movieDetailState.test {
            assertTrue(awaitItem() is UIState.Loading)

            // sut.getHomeState()
            sut.getMovie(movieId = DataFactory.adventureMovie.id)

            assertTrue(awaitItem() is UIState.Success)
        }
    }

    @Test
    fun `getMovie generates correct state`() = runTest {
        val sut = HomeViewModel(
            popularMoviesUseCase = GetPopularMoviesUseCase(
                homeRepository = generateHomeRepositoryWithFakeRemoteDataSource(
                    popularMovieResponse = DataResult.Success(DataFactory.popularMoviesDTO),
                ),
            ),
        )

        sut.getHomeState()
        advanceUntilIdle()

        sut.movieDetailState.test {
            assertTrue(awaitItem() is UIState.Loading)

            sut.getMovie(movieId = DataFactory.adventureMovie.id)

            val movieState = (awaitItem() as UIState.Success).data

            assertEquals(DataFactory.adventureMovie.title, movieState.title)
        }
    }
}
