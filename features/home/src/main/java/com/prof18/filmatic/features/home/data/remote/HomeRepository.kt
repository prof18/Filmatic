package com.prof18.filmatic.features.home.data.remote

import com.m2f.archer.crud.GetRepository
import com.m2f.archer.crud.getDataSource
import com.m2f.archer.failure.DataEmpty
import com.m2f.archer.failure.DataNotFound
import com.m2f.archer.failure.NetworkFailure
import com.m2f.archer.mapper.map
import com.m2f.archer.repository.toRepository
import com.prof18.filmatic.core.net.ConnectivityChecker
import com.prof18.filmatic.features.home.data.remote.dto.PopularMoviesDTO
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.domain.mapper.toMovie
import java.net.HttpURLConnection

fun getHomeRepository(
    homeService: HomeApiService,
    connectivityChecker: ConnectivityChecker
): GetRepository<Unit, List<Movie>> =
    getDataSource<Unit, PopularMoviesDTO> {
        if (!connectivityChecker.hasInternetAccess()) {
            raise(NetworkFailure.NoConnection)
        }
        val result = homeService.getPopularMovies()
        if (result.isSuccessful) {
            result.body() ?: raise(DataEmpty)
        } else {
            raise(
                when (result.code()) {
                    HttpURLConnection.HTTP_NOT_FOUND -> DataNotFound
                    // 401
                    HttpURLConnection.HTTP_UNAUTHORIZED -> NetworkFailure.Unauthorised
                    // 503
                    HttpURLConnection.HTTP_UNAVAILABLE, HttpURLConnection.HTTP_INTERNAL_ERROR -> NetworkFailure.ServerFailure
                    else -> NetworkFailure.UnhandledNetworkFailure
                }
            )
        }
    }.map { popularMoviesDTO ->
        popularMoviesDTO
            .movieResults
            .map { movieDTO ->
                movieDTO.toMovie()
            }
    }.toRepository()
