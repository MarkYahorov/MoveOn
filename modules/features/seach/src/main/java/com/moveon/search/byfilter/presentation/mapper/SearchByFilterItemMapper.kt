package com.moveon.search.byfilter.presentation.mapper

import com.moveon.common.mapper.GenresMapper
import com.moveon.common.mapper.ShowTypeMapper
import com.moveon.common.mapper.YearMapper
import com.moveon.search.byfilter.data.data.response.SearchFilterItemResponse
import com.moveon.search.byfilter.presentation.data.SearchByFilterItemPresentation
import javax.inject.Inject

class SearchByFilterItemMapper @Inject constructor(
    private val showTypeMapper: ShowTypeMapper,
    private val genresMapper: GenresMapper,
    private val yearMapper: YearMapper
) {

    fun map(response: SearchFilterItemResponse): SearchByFilterItemPresentation {
        return with(response) {
            SearchByFilterItemPresentation(
                type = showTypeMapper.map(type),
                title = if (title != originalTitle) "$title ($originalTitle)" else title,
                imdbId = imdbId,
                overview = overview,
                tmdbId = tmdbId,
                genres = genres.map { genresMapper.map(it) },
                year = yearMapper.map(type, year, firstYear, lastYear)
            )
        }
    }
}