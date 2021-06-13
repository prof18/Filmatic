package com.prof18.filmatic.features.home.fakes

import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.features.home.data.datasource.HomeRemoteDataSource
import com.prof18.filmatic.features.home.data.remote.dto.PopularMoviesDTO

class FakeHomeRemoteDataSource(
    private val popularMovieResponse: DataResult<PopularMoviesDTO>
) : HomeRemoteDataSource {

    override suspend fun getPopularMovies(): DataResult<PopularMoviesDTO> {
        return popularMovieResponse
    }
}
