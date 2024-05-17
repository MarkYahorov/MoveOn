package com.moveon.search.byfilter.presentation.bottomsheet.container

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moveon.common.CommonConst.DEFAULT_COUNTRY_CODE
import com.moveon.common.data.CountryItemPresentation
import com.moveon.common.data.CountryServicePresentation
import com.moveon.search.byfilter.data.data.request.SearchByFilterRequest
import com.moveon.search.byfilter.presentation.data.SearchByFilterType
import com.moveon.search.byfilter.presentation.data.toRequest

class SearchByFilterBottomSheetContainer(
    private val onHideBottomSheet: () -> Unit,
    private val onBtnClicked: () -> Unit
) {

    var keyword: String by mutableStateOf("")
    var countriesList by mutableStateOf(emptyList<CountryItemPresentation>())
    var selectedCountry by mutableStateOf<CountryItemPresentation?>(null)

    var services by mutableStateOf(emptyList<CountryServicePresentation>())
    var selectedService by mutableStateOf<CountryServicePresentation?>(null)

    var selectedType: SearchByFilterType by mutableStateOf(SearchByFilterType.All)

    fun setNewCountries(countries: List<CountryItemPresentation>) {
        countriesList = countries
        selectedCountry = countriesList.find { it.countryCode == DEFAULT_COUNTRY_CODE }
            ?: countriesList.first()

        updateServices()
    }

    private fun updateServices() {
        selectedCountry?.let { country ->
            services = country.services
            selectedService = country.services.first()
        }
    }

    fun onServiceChanged(newService: CountryServicePresentation) {
        selectedService = newService
    }

    fun onCountryChanged(newCountry: CountryItemPresentation) {
        selectedCountry = newCountry
        updateServices()
    }

    fun hideBottomSheet() {
        onHideBottomSheet.invoke()
    }

    fun onKeywordChanged(newKeyword: String) {
        keyword = newKeyword
    }

    fun onSelectedTypeChanged(newType: SearchByFilterType) {
        selectedType = newType
    }

    fun onBtnClicked() {
        onBtnClicked.invoke()
        onHideBottomSheet.invoke()
    }

    fun createSearchRequest(): SearchByFilterRequest {
        return SearchByFilterRequest(
            service = selectedService?.id.orEmpty(),
            country = selectedCountry?.countryCode.orEmpty(),
            keyword = keyword,
            showType = selectedType.toRequest()
        )
    }

    fun isRequestFieldsAreCorrect(): Boolean {
        return selectedCountry != null && selectedService != null
    }
}