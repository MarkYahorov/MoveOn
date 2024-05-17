package com.moveon.search.byfilter.presentation.data

import com.moveon.ui_core.data.GenresPresentation
import com.moveon.ui_core.data.ShowType
import com.moveon.ui_core.data.StatusPresentation

data class SearchByFilterItemPresentation(
    val type: ShowType,
    val title: String,
    val overview: String?,
    val imdbId: String,
    val tmdbId: Int,
    val year: String?,
    val genres: List<GenresPresentation>
)
