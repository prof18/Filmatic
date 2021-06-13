package com.prof18.filmatic.features.home.data.testutils

import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.features.home.data.HomeRepositoryImpl
import com.prof18.filmatic.features.home.data.remote.dto.PopularMoviesDTO
import com.prof18.filmatic.features.home.domain.HomeRepository
import com.prof18.filmatic.features.home.fakes.FakeHomeRemoteDataSource
import com.prof18.filmatic.libraries.testshared.testCoroutineDispatcherProvider

fun generateHomeRepositoryWithFakeRemoteDataSource(
    popularMovieResponse: DataResult<PopularMoviesDTO>
): HomeRepository {
    return HomeRepositoryImpl(
        homeRemoteDataSource = FakeHomeRemoteDataSource(
            popularMovieResponse = popularMovieResponse
        ),
        dispatcherProvider = testCoroutineDispatcherProvider
    )
}
