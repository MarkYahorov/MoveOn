package com.moveon.common.mapper

import com.moveon.common.data.ShowTypeResponse
import com.moveon.ui_core.data.ShowType
import javax.inject.Inject

class ShowTypeMapper @Inject constructor() {

    fun map(type: ShowTypeResponse): ShowType {
        return when (type) {
            ShowTypeResponse.Movie -> ShowType.Movie
            ShowTypeResponse.Series -> ShowType.Series
        }
    }
}