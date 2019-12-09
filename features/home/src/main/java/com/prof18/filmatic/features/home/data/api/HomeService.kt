/*
 * Copyright 2019 Marco Gomiero
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

package com.prof18.filmatic.features.home.data.api

import com.prof18.filmatic.features.home.data.discovery.model.GenreResponse
import com.prof18.filmatic.features.home.data.popular.model.PopularMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {

    @GET("movie/popular")
    suspend fun getPopularMovies(@Query("language") locale: String): PopularMovieResponse

    @GET("genre/movie/list")
    suspend fun getGenres(@Query("language") locale: String): GenreResponse

}