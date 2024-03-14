package com.moveon.changes.presentation.mappers

import com.moveon.changes.presentation.data.CountryItemPresentation
import com.moveon.network.network.data.CountryItemResponse
import javax.inject.Inject

class CountryItemMapper @Inject constructor(private val servicesMapper: ServiceMapper) {
    fun map(itemResponse: CountryItemResponse): CountryItemPresentation {
        return with(itemResponse) {
            CountryItemPresentation(
                countryCode = countryCode,
                title = name,
                services = services.map { servicesMapper.map(it) }
            )
        }
    }
}