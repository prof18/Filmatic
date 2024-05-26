package com.prof18.filmatic.features.home.domain.usecases

import arrow.core.Either
import com.m2f.archer.crud.GetRepository
import com.m2f.archer.crud.get
import com.m2f.archer.failure.Failure
import com.prof18.filmatic.core.architecture.DataResult
import com.prof18.filmatic.core.architecture.UseCase
import com.prof18.filmatic.features.home.domain.entities.Movie
import javax.inject.Inject

open class GetPopularMoviesUseCase @Inject constructor(
    private val homeRepository: @JvmSuppressWildcards GetRepository<Unit, List<Movie>>,
) {
    suspend operator fun invoke(): Either<Failure, List<Movie>> {
        return homeRepository.get()
    }
}
