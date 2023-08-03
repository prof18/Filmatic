package com.prof18.filmatic.features.home.domain.mapper

import com.prof18.filmatic.core.net.NetConstants
import com.prof18.filmatic.features.home.data.remote.dto.MovieDTO
import com.prof18.filmatic.features.home.domain.entities.Movie

fun MovieDTO.toMovie(): Movie {
    return Movie(
        id = this.id,
        title = this.title,
        overview = this.overview,
        posterPath = "${NetConstants.BASE_PICTURE_ADDRESS}/${this.backdropPath}",
    )
}
