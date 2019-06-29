package com.prof18.filmatic.features.home.data.popular.model

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("voteCount") val voteCount: Long,
    @SerializedName("id") val id: Int,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("title") val title: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("poster_path") val posterPath: String,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("genre_ids") val genreId: List<Long>,
    @SerializedName("backdrop_path") val backdropPath: String,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val releaseDate: String
)