package com.moveon.detail.presentation.mapper

import com.moveon.common.mapper.ShowTypeMapper
import com.moveon.common.mapper.StatusMapper
import com.moveon.common.mapper.YearMapper
import com.moveon.detail.data.response.DetailResponse
import com.moveon.detail.presentation.data.DetailPresentation
import javax.inject.Inject

class DetailMapper @Inject constructor(
    private val showTypeMapper: ShowTypeMapper,
    private val statusMapper: StatusMapper,
    private val yearMapper: YearMapper,
    private val seasonsMapper: SeasonsMapper
) {

    fun map(response: DetailResponse): DetailPresentation {
        return with(response) {
            DetailPresentation(
                type = showTypeMapper.map(type),
                title = if (originalTitle == title) title else "$title ($originalTitle)",
                tmdbId = tmdbId,
                year = yearMapper.map(type, year, firstYear, lastYear),
                imdbId = imdbId,
                creators = creators,
                directors = directors,
                episodesCount = episodesCount,
                seasonsCount = seasonsCount,
                status = statusMapper.map(status.status),
                seasons = seasons?.map { seasonsMapper.map(it) }.orEmpty(),
                description = description,
                cast = cast
            )
        }
    }
}