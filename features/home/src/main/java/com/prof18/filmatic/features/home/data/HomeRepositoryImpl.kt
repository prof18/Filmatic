package com.prof18.filmatic.features.home.data

import com.prof18.filmatic.core.architecture.CoroutineDispatcherProvider
import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.features.home.data.datasource.HomeRemoteDataSource
import com.prof18.filmatic.features.home.domain.HomeRepository
import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.domain.mapper.toMovie
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HomeRepositoryImpl @Inject constructor(
    private val homeRemoteDataSource: HomeRemoteDataSource,
    private val dispatcherProvider: CoroutineDispatcherProvider
) : HomeRepository {

    override suspend fun getPopularMovies(): DataResult<List<Movie>> =
        withContext(dispatcherProvider.io) {
            val popularMovieResult = homeRemoteDataSource.getPopularMovies()
            if (popularMovieResult is DataResult.Error) {
                return@withContext DataResult.Error(popularMovieResult.error)
            }
            return@withContext DataResult.Success(
                (popularMovieResult as DataResult.Success).data
                    .movieResults
                    .map { movieDTO ->
                        movieDTO.toMovie()
                    }
            )
        }
}
