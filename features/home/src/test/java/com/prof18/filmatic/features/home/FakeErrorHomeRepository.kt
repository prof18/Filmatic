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

package com.prof18.filmatic.features.home

import com.prof18.filmatic.core.architecture.Result
import com.prof18.filmatic.features.home.domain.HomeRepository
import com.prof18.filmatic.features.home.domain.entities.Genre
import com.prof18.filmatic.features.home.domain.entities.Movie
import java.io.IOException

class FakeErrorHomeRepository : HomeRepository {
    var exception = IOException()
        set(value) {
            errorResponse = Result.Error(value)
        }

    private var errorResponse = Result.Error(exception)

    override suspend fun getPopularMovies(): Result<List<Movie>> {
        return errorResponse
    }

    override suspend fun getGenres(): Result<List<Genre>> {
        return errorResponse
    }
}