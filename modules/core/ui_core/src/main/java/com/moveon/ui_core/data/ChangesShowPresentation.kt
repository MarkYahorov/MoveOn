package com.moveon.ui_core.data

data class ChangesShowPresentation(
    val type: ShowType,
    val title: String,
    val overview: String?,
    val imdbId: String,
    val tmdbId: Int,
    val year: String?,
    val originalTitle: String,
    val genres: List<GenresPresentation>,
    val status: StatusPresentation?,
    val seriesInfo: List<String>
)

data class GenresPresentation(
    val id: String,
    val name: String
)
