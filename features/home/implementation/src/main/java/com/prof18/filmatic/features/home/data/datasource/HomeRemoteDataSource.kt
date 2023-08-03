package com.prof18.filmatic.features.home.data.datasource

import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.features.home.data.remote.dto.PopularMoviesDTO

interface HomeRemoteDataSource {
    suspend fun getPopularMovies(): DataResult<PopularMoviesDTO>
}
