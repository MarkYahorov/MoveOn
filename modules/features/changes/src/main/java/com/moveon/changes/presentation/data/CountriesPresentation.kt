package com.moveon.changes.presentation.data

import com.moveon.ui_core.data.DropDownItem


data class CountryItemPresentation(
    val countryCode: String,
    override val title: String,
    val services: List<CountryServicePresentation>
) : DropDownItem

data class CountryServicePresentation(
    val id: String,
    override val title: String,
    val images: ServiceImagesPresentation
) : DropDownItem

data class ServiceImagesPresentation(
    val lightThemeImage: String,
    val darkThemeImage: String
)