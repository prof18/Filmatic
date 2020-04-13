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

import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.features.home.data.models.MovieModel
import com.prof18.filmatic.features.home.data.remote.HomeRemoteDataSource
import com.prof18.filmatic.features.home.remote.api.HomeService
import com.prof18.filmatic.features.home.remote.mapper.MovieResultMapper
import timber.log.Timber
import javax.inject.Inject

class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeService: HomeService,
    private val mapper: MovieResultMapper
) : HomeRemoteDataSource {
    override suspend fun getPopularMovies(): Result<List<MovieModel>> {
        return try {
            val result = homeService.getPopularMovies()
            val movies = result.movieResults
                .map { movieResult ->
                    mapper.map(movieResult)
                }
            Result.Success(movies)
        } catch (e: Exception) {
            Timber.w(e)
            Result.Error(e)
        }
    }
}