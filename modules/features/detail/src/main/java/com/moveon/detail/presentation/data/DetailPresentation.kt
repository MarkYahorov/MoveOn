package com.moveon.detail.presentation.data

import com.moveon.ui_core.data.ShowType
import com.moveon.ui_core.data.StatusPresentation

data class DetailPresentation(
    val type: ShowType,
    val title: String,
    val year: String,
    val imdbId: String,
    val tmdbId: Int,
    val directors: List<String>?,
    val creators: List<String>?,
    val seasonsCount: Int?,
    val episodesCount: Int?,
    val status: StatusPresentation,
    val seasons: List<DetailSeasonPresentation>,
    val description: String,
    val cast: List<String>
)
