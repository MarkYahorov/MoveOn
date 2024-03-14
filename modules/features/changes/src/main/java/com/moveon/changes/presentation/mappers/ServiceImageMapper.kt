package com.moveon.changes.presentation.mappers

import com.moveon.network.network.data.ServiceImagesResponse
import com.moveon.changes.presentation.data.ServiceImagesPresentation
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