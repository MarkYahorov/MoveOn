package com.moveon.changes.presentation.mappers

import com.moveon.changes.data.data.response.ChangesShowResponse
import com.moveon.common.mapper.GenresMapper
import com.moveon.common.mapper.ShowTypeMapper
import com.moveon.common.mapper.StatusMapper
import com.moveon.common.mapper.YearMapper
import com.moveon.ui_core.data.ChangesShowPresentation
import javax.inject.Inject

class ShowMapper @Inject constructor(
    private val genresMapper: GenresMapper,
    private val showTypeMapper: ShowTypeMapper,
    private val statusMapper: StatusMapper,
    private val yearMapper: YearMapper
) {

    fun map(show: ChangesShowResponse): ChangesShowPresentation {
        return with(show) {
            ChangesShowPresentation(
                type = showTypeMapper.map(type),
                imdbId = imdbId,
                originalTitle = originalTitle,
                overview = overview,
                title = title,
                year = yearMapper.map(type, year, firstYear, lastYear),
                tmdbId = tmdbId,
                status = status?.let { statusMapper.map(status.status) },
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
}