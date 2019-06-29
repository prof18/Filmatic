package com.prof18.filmatic.features.home.data.popular

import com.prof18.filmatic.features.home.data.popular.model.Movie

class PopularRepository(private val dataSource: PopularRemoteDataSource) {

    suspend fun getPopularMovies(): List<Movie> {
        try {
            val result = dataSource.getPopularMovies()
            return result.popularMovies
        } catch (e: Exception) {
            // TODO: think about doing something else
            throw e
        }
    }
}