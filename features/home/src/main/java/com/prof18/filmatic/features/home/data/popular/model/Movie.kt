package com.prof18.filmatic.features.home.data.popular.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

// TODO: just only a few fields to testing dagger
data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("vote_average") val vote_average: Double,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("adult") val adult: Boolean
)