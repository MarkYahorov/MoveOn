package com.moveon.changes.presentation.mappers

import com.moveon.changes.data.data.response.GenresResponse
import com.moveon.ui_core.data.GenresPresentation
import javax.inject.Inject

class GenderMapper @Inject constructor() {
    fun map(response: GenresResponse): GenresPresentation {
        return with(response) {
            GenresPresentation(id, name)
        }
    }
}