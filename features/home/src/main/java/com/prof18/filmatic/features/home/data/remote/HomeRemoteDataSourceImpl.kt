package com.prof18.filmatic.features.home.data.remote

import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.core.error.ErrorMapper
import com.prof18.filmatic.core.error.NetworkError
import com.prof18.filmatic.core.net.ConnectivityChecker
import com.prof18.filmatic.features.home.data.datasource.HomeRemoteDataSource
import com.prof18.filmatic.features.home.data.remote.dto.PopularMoviesDTO
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRemoteDataSourceImpl @Inject constructor(
    private val homeService: HomeApiService,
    private val connectivityChecker: ConnectivityChecker,
    private val errorMapper: ErrorMapper,
) : HomeRemoteDataSource {

    override suspend fun getPopularMovies(): DataResult<PopularMoviesDTO> {
        if (!connectivityChecker.hasInternetAccess()) {
            return DataResult.Error(NetworkError.ConnectionNotAvailable)
        }
        // Suppress because a generic mapping is done
        @Suppress("TooGenericExceptionCaught")
        return try {
            DataResult.Success(homeService.getPopularMovies())
        } catch (ex: Exception) {
            Timber.e(ex, "Error during getting vehicles")
            DataResult.Error(errorMapper.mapError(ex))
        }
    }
}
