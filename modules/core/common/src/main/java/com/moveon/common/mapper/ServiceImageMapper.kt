package com.moveon.common.mapper

import com.moveon.network.network.data.ServiceImagesResponse
import com.moveon.common.data.ServiceImagesPresentation
import javax.inject.Inject

class ServiceImageMapper @Inject constructor() {

    fun map(imageResponse: ServiceImagesResponse): ServiceImagesPresentation {
        return with(imageResponse) {
            ServiceImagesPresentation(
                lightThemeImage, darkThemeImage
            )
        }
    }
}