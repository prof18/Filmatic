package com.prof18.filmatic.features.home.data.discovery.model

import com.google.gson.annotations.SerializedName

data class Genre (
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)