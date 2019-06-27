package com.prof18.filmatic.features.home.data.popular.model

import com.google.gson.annotations.SerializedName

data class PopularMovieResponse(
    @SerializedName("results") val popularMovies: List<Movie>
)