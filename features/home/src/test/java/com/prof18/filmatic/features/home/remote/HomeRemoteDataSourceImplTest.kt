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

package com.prof18.filmatic.features.home.remote

import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.features.home.data.models.MovieModel
import com.prof18.filmatic.features.home.remote.api.HomeService
import com.prof18.filmatic.features.home.remote.mapper.MovieResultMapper
import com.prof18.filmatic.features.home.remote.model.MovieResult
import com.prof18.filmatic.features.home.remote.model.PopularMoviesResult
import com.prof18.filmatic.features.home.DataFactory
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.io.IOException

@RunWith(JUnit4::class)
class HomeRemoteDataSourceImplTest {

    private val homeService = mock<HomeService>()
    private val mapper = mock<MovieResultMapper>()
    private val remoteDataSource = HomeRemoteDataSourceImpl(homeService, mapper)

    @Test
    fun getPopularMoviesReturnsData() = runBlocking {
        val moviesResult = DataFactory.getMovieResult()
        val popularMovieResult = DataFactory.getPopularMovieResult(listOf(moviesResult))

        val movieModel = DataFactory.getMovieModel()

        stubMapper(moviesResult, movieModel)
        stubHomeServiceSuccess(popularMovieResult)

        val dataSourceResult = remoteDataSource.getPopularMovies()
        val movieModelList = (dataSourceResult as Result.Success).data

        verify(homeService).getPopularMovies()
        assertEquals(movieModelList[0].backdropPath, movieModel.backdropPath)
        assertEquals(movieModelList[0].genreIds, movieModel.genreIds)
        assertEquals(movieModelList[0].id, movieModel.id)
        assertEquals(movieModelList[0].overview, movieModel.overview)
        assertEquals(movieModelList[0].posterPath, movieModel.posterPath)
        assertEquals(movieModelList[0].releaseDate, movieModel.releaseDate)
        assertEquals(movieModelList[0].title, movieModel.title)
    }

    @Test
    fun getPopularMoviesReturnsError() = runBlocking {
        val exception = IOException("no network")
        stubHomeServiceReturnsException(exception)

        val result = remoteDataSource.getPopularMovies()
        val dataSourceException = (result as Result.Error).exception

        verify(homeService).getPopularMovies()
        assertEquals(dataSourceException, exception)
    }

    private fun stubHomeServiceSuccess(popularMovieResult: PopularMoviesResult) = runBlocking {
        whenever(homeService.getPopularMovies())
            .thenReturn(popularMovieResult)
    }

    private fun stubHomeServiceReturnsException(exception: Exception) = runBlocking {
        doThrow(exception).whenever(homeService).getPopularMovies()
    }

    private fun stubMapper(movieResult: MovieResult, model: MovieModel) {
        whenever(mapper.map(movieResult))
            .thenReturn(model)
    }
}