package com.prof18.filmatic.features.home.data

import com.prof18.filmatic.DataFactory
import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.core.error.NetworkError
import com.prof18.filmatic.features.home.fakes.FakeHomeRemoteDataSource
import com.prof18.filmatic.libraries.testshared.testCoroutineDispatcherProvider
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test

class HomeRepositoryImplTest {

    private val dispatcher = testCoroutineDispatcherProvider.main

    @Test
    fun `getPopularMovies returns error when popular movies api returns error`() =
        runTest(dispatcher) {
            val sut = HomeRepositoryImpl(
                homeRemoteDataSource = FakeHomeRemoteDataSource(
                    popularMovieResponse = DataResult.Error(NetworkError.NotFound)
                ),
                dispatcherProvider = testCoroutineDispatcherProvider
            )

            val result = sut.getPopularMovies()

            assertEquals(NetworkError.NotFound, (result as DataResult.Error).error)
        }

    @Test
    fun `getPopularMovies returns success`() = runTest(dispatcher) {

        val sut = HomeRepositoryImpl(
            homeRemoteDataSource = FakeHomeRemoteDataSource(
                popularMovieResponse = DataResult.Success(DataFactory.popularMoviesDTO)
            ),
            dispatcherProvider = testCoroutineDispatcherProvider
        )

        val result = sut.getPopularMovies()

        val actionAdventure = (result as DataResult.Success).data
            .firstOrNull { it.id == DataFactory.actionAdventureMovie.id }
        assertNotNull(actionAdventure)
    }
}
