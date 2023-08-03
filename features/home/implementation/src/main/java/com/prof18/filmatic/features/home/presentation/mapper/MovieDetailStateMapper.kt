package com.prof18.filmatic.features.home.presentation.mapper

import com.prof18.filmatic.features.home.domain.entities.Movie
import com.prof18.filmatic.features.home.presentation.state.MovieDetailState

fun Movie.toDetailState(): MovieDetailState {
    return MovieDetailState(
        title = this.title,
        summary = this.overview,
        imageUrl = this.posterPath,
    )
}
