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

package com.prof18.filmatic.features.home.data.popular

import com.prof18.filmatic.core.UserPreferenceManager
import com.prof18.filmatic.features.home.data.api.HomeService
import com.prof18.filmatic.features.home.data.popular.model.PopularMovieResponse
import java.lang.Exception
import javax.inject.Inject

class PopularRemoteDataSource @Inject constructor(
    private val service: HomeService,
    private val userPreferenceManager: UserPreferenceManager
) {

    suspend fun getPopularMovies(): PopularMovieResponse {
        try {
            return service.getPopularMovies(userPreferenceManager.getUserPreferredLocale())
        } catch (e: Exception) {
            // TODO: think about doing something else
            throw e
        }
    }
}