package com.moveon.common.mapper

import com.moveon.common.data.GenresResponse
import com.moveon.ui_core.data.GenresPresentation
import javax.inject.Inject

class GenresMapper @Inject constructor() {
    fun map(response: GenresResponse): GenresPresentation {
        return with(response) {
            GenresPresentation(id, name)
        }
    }
}