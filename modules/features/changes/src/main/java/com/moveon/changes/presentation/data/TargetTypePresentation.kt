package com.moveon.changes.presentation.data

import com.moveon.changes.data.data.request.TargetType
import com.moveon.ui_core.data.DropDownItem

enum class TargetTypePresentation(override val title: String) : DropDownItem {
    Show("Show"),
    Movie("Movie"),
    Series("Series"),
    Season("Season"),
    Episode("Episode")
}

fun TargetTypePresentation.mapToRequest(): TargetType {
    return when (this) {
        TargetTypePresentation.Season -> TargetType.Season
        TargetTypePresentation.Episode -> TargetType.Episode
        TargetTypePresentation.Movie -> TargetType.Movie
        TargetTypePresentation.Series -> TargetType.Series
        TargetTypePresentation.Show -> TargetType.Show
    }
}