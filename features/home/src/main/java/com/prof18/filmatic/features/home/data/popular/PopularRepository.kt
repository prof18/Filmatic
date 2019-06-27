package com.prof18.filmatic.features.home.data.popular

import com.prof18.filmatic.features.home.data.popular.model.Movie

class PopularRepository(private val dataSource: PopularRemoteDataSource) {


    suspend fun getpopularMovies(): List<Movie> {
        try {
            val result = dataSource.getPopularMovies()
            return result.popularMovies
        } catch (e: Exception) {
            return mutableListOf()
        }
    }

}