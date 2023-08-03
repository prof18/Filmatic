package com.prof18.filmatic.features.home.data.remote.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PopularMoviesDTO(
    @Json(name = "page") val page: Int,
    @Json(name = "results") val movieResults: List<MovieDTO>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int,
)
