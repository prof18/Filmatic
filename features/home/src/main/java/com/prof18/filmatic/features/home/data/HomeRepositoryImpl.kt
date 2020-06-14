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
import com.prof18.filmatic.features.home.data.mapper.GenreModelMapper
import com.prof18.filmatic.features.home.data.mapper.MovieModelMapper
import com.prof18.filmatic.features.home.data.remote.HomeRemoteDataSource
import com.prof18.filmatic.features.home.domain.HomeRepository
import com.prof18.filmatic.features.home.domain.entities.Genre
import com.prof18.filmatic.features.home.domain.entities.Movie
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val movieModelMapper: MovieModelMapper,
    private val genreModelMapper: GenreModelMapper
) : HomeRepository {

    override suspend fun getPopularMovies(): Result<List<Movie>> {
        val result = homeRemoteDataSource.getPopularMovies()

        when (result) {
            is Result.Success -> {
                val popularMovies = result.data
                val movies = popularMovies
                    .map { movieModel ->
                        movieModelMapper.map(movieModel)
                    }
                return Result.Success(movies)
            }
            is Result.Error -> {
                return Result.Error(result.exception)
            }
        }
    }

    override suspend fun getGenres(): Result<List<Genre>> {
        val result = homeRemoteDataSource.getAllGenres()

        when(result) {
            is Result.Success -> {
                val genres = result.data.genres.map {
                    genreModelMapper.map(it)
                }
                return Result.Success(genres)
            }
            is Result.Error -> return Result.Error(result.exception)
        }
    }
}