package com.moveon.detail.data.response

import com.moveon.common.data.SeriesStatusResponse
import com.moveon.common.data.ShowTypeResponse

data class DetailResponse(
    val type: ShowTypeResponse,
    val title: String,
    val year: Int?,
    val firstYear: Int?,
    val lastYear: Int?,
    val imdbId: String,
    val tmdbId: String,
    val originalTitle: String,
    val directors: List<String>?,
    val creators: List<String>?,
    val seasonsCount: Int?,
    val episodesCount: Int?,
    val status: SeriesStatusResponse
)
