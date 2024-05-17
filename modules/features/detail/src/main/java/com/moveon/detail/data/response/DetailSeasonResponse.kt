package com.moveon.detail.data.response

import com.google.gson.annotations.SerializedName

data class DetailSeasonResponse(
    @SerializedName("title")
    val title: String,
    @SerializedName("firstAirYear")
    val firstYear: Int,
    @SerializedName("lastAirYear")
    val lastYear: Int,
    @SerializedName("episodes")
    val episodes: List<DetailEpisodeResponse>
)
