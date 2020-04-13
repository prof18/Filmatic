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

package com.prof18.filmatic.features.home.data

import com.nhaarman.mockitokotlin2.*
import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.features.home.data.mapper.MovieModelMapper
import com.prof18.filmatic.features.home.data.models.MovieModel
import com.prof18.filmatic.features.home.data.remote.HomeRemoteDataSource
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.utils.DataFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class HomeRepositoryImplTest {

    private val mapper = mock<MovieModelMapper>()
    private val homeRemoteDataSource = mock<HomeRemoteDataSource>()
    private val repository = HomeRepositoryImpl(homeRemoteDataSource, mapper)

    @Test
    fun getPopularMoviesReturnsData() = runBlocking {
        val movieModel = DataFactory.getMovieModel()
        val movie = DataFactory.getMovie()

        stubHomeRemoteDataSource(listOf(movieModel))
        stubMovieModelMapper(movieModel, movie)

        val result = repository.getPopularMovies()
        val movies = (result as Result.Success).data

        verify(homeRemoteDataSource).getPopularMovies()

        assertEquals(movies[0].id, movie.id)
        assertEquals(movies[0].title, movie.title)
        assertEquals(movies[0]._backdropPath, movie._backdropPath)
    }

    @Test
    fun getPopularMoviesReturnsError() = runBlocking {
        val exception = IOException("No network")
        stubHomeRemoteDataSourceError(exception)

        val result = repository.getPopularMovies()
        val exceptionResult = (result as Result.Error).exception

        verify(homeRemoteDataSource).getPopularMovies()

        assertEquals(exception, exceptionResult)
    }

    private fun stubHomeRemoteDataSource(movieModels: List<MovieModel>) = runBlocking {
        whenever(homeRemoteDataSource.getPopularMovies()).thenReturn(Result.Success(movieModels))
    }

    private fun stubHomeRemoteDataSourceError(e: IOException) = runBlocking {
        whenever(homeRemoteDataSource.getPopularMovies())
            .thenReturn(Result.Error(e))
    }

    private fun stubMovieModelMapper(movieModel: MovieModel, movie: Movie) {
        whenever(mapper.map(movieModel)).thenReturn(movie)
    }
}