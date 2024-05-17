package com.moveon.common.mapper

import com.moveon.network.network.data.CountryServiceResponse
import com.moveon.common.data.CountryServicePresentation
import javax.inject.Inject

class ServiceMapper @Inject constructor(private val serviceImageMapper: ServiceImageMapper) {

    fun map(serviceResponse: CountryServiceResponse): CountryServicePresentation {
        return with(serviceResponse) {
            CountryServicePresentation(
                id = id,
                title = name,
                images = serviceImageMapper.map(images)
            )
        }
    }
}