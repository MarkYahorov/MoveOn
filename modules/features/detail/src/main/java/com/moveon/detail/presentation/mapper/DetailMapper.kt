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
    private val yearMapper: YearMapper
) {

    fun map(response: DetailResponse): DetailPresentation {
        return with(response) {
            DetailPresentation(
                type = showTypeMapper.map(type),
                title = title,
                tmdbId = tmdbId,
                year = yearMapper.map(type, year, firstYear, lastYear),
                imdbId = imdbId,
                originalTitle = originalTitle,
                creators = creators,
                directors = directors,
                episodesCount = episodesCount,
                seasonsCount = seasonsCount,
                status = statusMapper.map(status.status)
            )
        }
    }
}