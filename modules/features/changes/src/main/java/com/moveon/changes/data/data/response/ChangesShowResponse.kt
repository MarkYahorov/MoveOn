package com.moveon.changes.data.data.response

import com.google.gson.annotations.SerializedName
import com.moveon.common.data.GenresResponse
import com.moveon.common.data.SeriesStatusResponse
import com.moveon.common.data.ShowTypeResponse

data class ChangesShowResponse(
    @SerializedName("type")
    val type: ShowTypeResponse,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String?,
    @SerializedName("imdbId")
    val imdbId: String,
    @SerializedName("tmdbId")
    val tmdbId: Int,
    @SerializedName("originalTitle")
    val originalTitle: String,
    @SerializedName("genres")
    val genres: List<GenresResponse>,
    @SerializedName("status")
    val status: SeriesStatusResponse?,
    @SerializedName("year")
    val year: Int?,
    @SerializedName("firstAirYear")
    val firstYear: Int?,
    @SerializedName("lastAirYear")
    val lastYear: Int?,
    @SerializedName("episodeCount")
    val episodeCount: Int?,
    @SerializedName("seasonCount")
    val seasonsCount: Int?
)
