package com.moveon.changes.presentation.mappers

import com.moveon.changes.data.data.response.ChangeItemResponse
import com.moveon.pagination.data.PagingPageResponse
import com.moveon.ui_core.data.ChangesItemPresentation
import javax.inject.Inject

class ChangesMapper @Inject constructor(
    private val streamingChangesMapper: StreamingChangesMapper,
    private val showMapper: ShowMapper
) {

    fun map(response: PagingPageResponse<ChangeItemResponse>): PagingPageResponse<ChangesItemPresentation> {
        return with(response) {
            PagingPageResponse(
                hasKey, nextKey, data.map {
                    ChangesItemPresentation(
                        it.changes.map { streamingChangesMapper.map(it) },
                        show = showMapper.map(it.show)
                    )
                }
            )
        }
    }
}