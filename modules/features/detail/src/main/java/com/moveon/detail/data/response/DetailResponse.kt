package com.moveon.detail.data.response

import com.google.gson.annotations.SerializedName
import com.moveon.common.data.SeriesStatusResponse
import com.moveon.common.data.ShowTypeResponse

data class DetailResponse(
    @SerializedName("type")
    val type: ShowTypeResponse,
    @SerializedName("title")
    val title: String,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("firstAirYear")
    val firstYear: Int?,
    @SerializedName("lastAirYear")
    val lastYear: Int?,
    @SerializedName("imdbId")
    val imdbId: String,
    @SerializedName("tmdbId")
    val tmdbId: Int,
    @SerializedName("originalTitle")
    val originalTitle: String,
    @SerializedName("directors")
    val directors: List<String>?,
    @SerializedName("creators")
    val creators: List<String>?,
    @SerializedName("seasonCount")
    val seasonsCount: Int?,
    @SerializedName("episodeCount")
    val episodesCount: Int?,
    @SerializedName("status")
    val status: SeriesStatusResponse,
    @SerializedName("seasons")
    val seasons: List<DetailSeasonResponse>?,
    @SerializedName("overview")
    val description: String,
    @SerializedName("cast")
    val cast: List<String>
)
