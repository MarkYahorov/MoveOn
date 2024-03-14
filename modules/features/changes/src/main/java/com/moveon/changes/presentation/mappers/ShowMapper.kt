package com.moveon.changes.presentation.mappers

import com.moveon.changes.data.data.response.ChangesShowResponse
import com.moveon.changes.data.data.response.ShowTypeResponse
import com.moveon.changes.data.data.response.StatusResponse
import com.moveon.ui_core.data.ChangesShowPresentation
import com.moveon.ui_core.data.ShowType
import com.moveon.ui_core.data.StatusPresentation
import javax.inject.Inject

class ShowMapper @Inject constructor(private val genresMapper: GenderMapper) {

    fun map(show: ChangesShowResponse): ChangesShowPresentation {
        return with(show) {
            ChangesShowPresentation(
                type = mapType(type),
                imdbId = imdbId,
                originalTitle = originalTitle,
                overview = overview,
                title = title,
                year = when (type) {
                    ShowTypeResponse.Movie -> year?.toString()
                    ShowTypeResponse.Series -> "$firstYear - ${lastYear ?: "Current"}"
                }.orEmpty(),
                tmdbId = tmdbId,
                status = status?.let { mapStatus(status.status) },
                genres = genres.map { genresMapper.map(it) },
                seriesInfo = mutableListOf<String>().apply {
                    if (episodeCount != null) {
                        add("$episodeCount episodes")
                    }
                    if (seasonsCount != null) {
                        add("$seasonsCount seasons")
                    }
                }
            )
        }
    }

    private fun mapStatus(status: StatusResponse): StatusPresentation {
        return when (status) {
            StatusResponse.Ended -> StatusPresentation.Ended
            StatusResponse.Pilot -> StatusPresentation.Pilot
            StatusResponse.Planned -> StatusPresentation.Planned
            StatusResponse.Cancelled -> StatusPresentation.Cancelled
            StatusResponse.InProduction -> StatusPresentation.InProduction
            StatusResponse.Returning -> StatusPresentation.Returning
        }
    }

    private fun mapType(type: ShowTypeResponse): ShowType {
        return when (type) {
            ShowTypeResponse.Movie -> ShowType.Movie
            ShowTypeResponse.Series -> ShowType.Series
        }
    }
}