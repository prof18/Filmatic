package com.prof18.filmatic.features.home.data.discovery.model

import com.google.gson.annotations.SerializedName

data class GenreResponse (
    @SerializedName("genres") val genres: List<Genre>
)