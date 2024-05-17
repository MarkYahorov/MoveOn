package com.moveon.common.mapper

import com.moveon.common.data.CountryItemPresentation
import com.moveon.network.network.data.CountriesResponse
import javax.inject.Inject

class CountriesMapper @Inject constructor(
    private val countyItemMapper: CountryItemMapper
) {

    fun map(response: CountriesResponse): List<CountryItemPresentation> {
        return response.result.map {
            countyItemMapper.map(it)
        }
    }
}