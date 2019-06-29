package com.prof18.filmatic.features.home.data.popular

import com.prof18.filmatic.features.home.data.api.HomeService
import com.prof18.filmatic.features.home.data.popular.model.PopularMovieResponse
import java.lang.Exception
import javax.inject.Inject

class PopularRemoteDataSource (private val service: HomeService) {

    suspend fun getPopularMovies(): PopularMovieResponse {
        try {
            return service.getPopularMovies()
        } catch (e: Exception) {
            // TODO: think about doing something else
            throw e
        }
    }
}