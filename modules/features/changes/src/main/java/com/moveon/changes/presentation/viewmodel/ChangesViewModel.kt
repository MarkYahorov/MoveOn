package com.moveon.changes.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.paging.PagingData
import com.moveon.changes.data.repository.ChangesRepository
import com.moveon.changes.presentation.mappers.CountriesMapper
import com.moveon.core.fetcher.fetchData
import com.moveon.core.viewmodel.CoreViewModel
import com.moveon.network.network.data.CountriesResponse
import com.moveon.pagination.fetcher.fetchPagingData
import com.moveon.ui_core.data.ChangesItemPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@HiltViewModel
class ChangesViewModel @Inject constructor(
    repository: ChangesRepository,
    private val countriesMapper: CountriesMapper,
) : CoreViewModel() {

    var changingFlow: Flow<PagingData<ChangesItemPresentation>> by mutableStateOf(emptyFlow())

    var isBottomSheetVisible: Boolean by mutableStateOf(false)
    var isCountriesErrorVisible: Boolean by mutableStateOf(false)

    var isProgressVisible: Boolean by mutableStateOf(false)


    internal val bottomSheetContainer = ChangesBottomSheetStateContainer(
        hideBottomSheet = { isBottomSheetVisible = false },
        onSaveBtnClicked = { fetchChangesList() }
    )

    private val fetchCountries = fetchData(
        repository = repository,
        callFunction = ChangesRepository::getCountries,
        onSuccess = { response ->
            response?.let {
                handleSuccessCountriesResponse(it)
            }
        },
        onError = { handleCountriesError() }
    )

    private val fetchChanges = fetchPagingData(
        repository = repository,
        onSuccess = { isProgressVisible = false },
        onError = { isProgressVisible = false }
    )

    fun getCountries() {
        isProgressVisible = true
        fetchCountries.fetch()
    }

    fun onOpenBottomSheetClicked() {
        isBottomSheetVisible = true
    }

    private fun handleCountriesError() {
        isProgressVisible = false
        isCountriesErrorVisible = true
    }

    private fun handleSuccessCountriesResponse(response: CountriesResponse) {
        bottomSheetContainer.setNewCountries(countriesMapper.map(response))
        fetchChangesList()
    }

    private fun fetchChangesList() {
        changingFlow = fetchChanges.fetch(input = bottomSheetContainer.createRequestModel())
    }
}