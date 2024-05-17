package com.moveon.detail.presentation.mapper

import com.moveon.detail.data.response.DetailSeasonResponse
import com.moveon.detail.presentation.data.DetailSeasonPresentation
import javax.inject.Inject

class SeasonsMapper @Inject constructor() {

    fun map(response: DetailSeasonResponse): DetailSeasonPresentation {
        return with(response) {
            DetailSeasonPresentation(
                title = title,
                years = if (firstYear == lastYear) "$firstYear" else "$firstYear - $lastYear",
                episodes = episodes.map { it.title }
            )
        }
    }
}