package com.prof18.filmatic.features.home.domain

import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.features.home.domain.entities.Movie

interface HomeRepository {
    /**
     * Retrieve a list of popular movies from the network
     */
    suspend fun getPopularMovies(): DataResult<List<Movie>>
}
