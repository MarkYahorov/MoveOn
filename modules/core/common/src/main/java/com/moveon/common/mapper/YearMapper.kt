package com.moveon.common.mapper

import com.moveon.common.data.ShowTypeResponse
import javax.inject.Inject

class YearMapper @Inject constructor() {

    fun map(type: ShowTypeResponse, year: Int?, firstYear: Int?, lastYear: Int?): String {
        return when (type) {
            ShowTypeResponse.Movie -> year?.toString()
            ShowTypeResponse.Series -> "$firstYear - ${lastYear ?: "Current"}"
        }.orEmpty()
    }
}