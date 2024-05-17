package com.moveon.search.byfilter.presentation.mapper

import com.moveon.pagination.data.PagingPageResponse
import com.moveon.search.byfilter.data.data.response.SearchFilterItemResponse
import com.moveon.search.byfilter.presentation.data.SearchByFilterItemPresentation
import javax.inject.Inject

class SearchByFilterMapper @Inject constructor(private val itemMapper: SearchByFilterItemMapper) {

    fun map(
        response: PagingPageResponse<SearchFilterItemResponse>
    ): PagingPageResponse<SearchByFilterItemPresentation> {
        return with(response) {
            PagingPageResponse(
                hasKey = hasKey,
                nextKey = nextKey,
                data = data.map { itemMapper.map(it) }
            )
        }
    }
}