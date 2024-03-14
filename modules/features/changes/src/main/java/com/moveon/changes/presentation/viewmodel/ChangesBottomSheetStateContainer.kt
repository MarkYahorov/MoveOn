package com.moveon.changes.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moveon.changes.data.data.request.ChangesRequest
import com.moveon.changes.presentation.data.ChangeTypePresentation
import com.moveon.changes.presentation.data.CountryItemPresentation
import com.moveon.changes.presentation.data.CountryServicePresentation
import com.moveon.changes.presentation.data.TargetTypePresentation
import com.moveon.changes.presentation.data.mapToRequest

private const val DEFAULT_COUNTRY_CODE = "eu"

class ChangesBottomSheetStateContainer(
    private val hideBottomSheet: () -> Unit,
    private val onSaveBtnClicked: () -> Unit
) {

    var countriesList by mutableStateOf(emptyList<CountryItemPresentation>())
    var selectedCountry by mutableStateOf<CountryItemPresentation?>(null)

    var services by mutableStateOf(emptyList<CountryServicePresentation>())
    var selectedService by mutableStateOf<CountryServicePresentation?>(null)

    var targetType: TargetTypePresentation by mutableStateOf(TargetTypePresentation.Season)
    var changeType: ChangeTypePresentation by mutableStateOf(ChangeTypePresentation.New)

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

    fun onTargetTypeChanged(newTargetType: TargetTypePresentation) {
        targetType = newTargetType
    }

    fun onChangeTypeChanges(newChangesType: ChangeTypePresentation) {
        changeType = newChangesType
    }

    fun createRequestModel(): ChangesRequest {
        return ChangesRequest(
            changeType.mapToRequest(),
            selectedService?.id.orEmpty(),
            selectedCountry?.countryCode.orEmpty(),
            targetType.mapToRequest()
        )
    }

    fun hideBottomSheet() {
        hideBottomSheet.invoke()
    }

    fun onBottomSheetSaveBtnClicked() {
        onSaveBtnClicked.invoke()
    }

    fun isRequestFieldsAreCorrect(): Boolean {
        return selectedCountry != null && selectedService != null
    }
}