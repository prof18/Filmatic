package com.prof18.filmatic.features.home.data.popular

import com.prof18.filmatic.core.UserPreferenceManager
import com.prof18.filmatic.features.home.data.api.HomeService
import com.prof18.filmatic.features.home.data.popular.model.PopularMovieResponse
import java.lang.Exception

class PopularRemoteDataSource (private val service: HomeService, private val userPreferenceManager: UserPreferenceManager) {

    suspend fun getPopularMovies(): PopularMovieResponse {
        try {
            return service.getPopularMovies(userPreferenceManager.getUserPreferredLocale())
        } catch (e: Exception) {
            // TODO: think about doing something else
            throw e
        }
    }
}