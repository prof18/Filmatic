package com.prof18.filmatic.features.home.domain.usecases

import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.core.architecture.UseCase
import com.prof18.filmatic.features.home.domain.HomeRepository
import com.prof18.filmatic.features.home.domain.entities.Movie
import javax.inject.Inject

open class GetPopularMoviesUseCase @Inject constructor(
    private val homeRepository: HomeRepository,
) : UseCase<DataResult<List<Movie>>> {

    override suspend fun invoke(): DataResult<List<Movie>> {
        return homeRepository.getPopularMovies()
    }
}
