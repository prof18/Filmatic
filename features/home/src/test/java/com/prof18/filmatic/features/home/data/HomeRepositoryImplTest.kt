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

import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.features.home.FakeErrorHomeRemoteDataSource
import com.prof18.filmatic.features.home.FakeSuccessHomeRemoteDataSource
import com.prof18.filmatic.features.home.data.mapper.GenreModelMapper
import com.prof18.filmatic.features.home.data.mapper.MovieModelMapper
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeRepositoryImplTest {

    private val successRemoteDataSource = FakeSuccessHomeRemoteDataSource()
    private val errorRemoteDataSource = FakeErrorHomeRemoteDataSource()

    private val successRepository = HomeRepositoryImpl(
        homeRemoteDataSource = successRemoteDataSource,
        movieModelMapper = MovieModelMapper(),
        genreModelMapper = GenreModelMapper()
    )
    private val errorRepository = HomeRepositoryImpl(
        homeRemoteDataSource = errorRemoteDataSource,
        movieModelMapper = MovieModelMapper(),
        genreModelMapper = GenreModelMapper()
    )

    @Test
    fun getPopularMoviesReturnsData() = runBlocking {
        val result = successRepository.getPopularMovies()
        val movies = (result as Result.Success).data

        val remoteMovie = successRemoteDataSource.movieModels[0]

        assertEquals(movies[0].id, remoteMovie.id)
        assertEquals(movies[0].title, remoteMovie.title)
        assertEquals(movies[0]._backdropPath, remoteMovie.backdropPath)
    }

    @Test
    fun getPopularMoviesReturnsError() = runBlocking {

        val result = errorRepository.getPopularMovies()
        val exceptionResult = (result as Result.Error).exception


        assertEquals(errorRemoteDataSource.exception, exceptionResult)
    }


}