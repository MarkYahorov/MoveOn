package com.moveon.detail.data.request

import com.google.gson.annotations.SerializedName

data class DetailRequest(
    @SerializedName("imdbId")
    val imdbId: String?,
    @SerializedName("tmdbId")
    val tmdbId: Int?
)