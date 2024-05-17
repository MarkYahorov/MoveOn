package com.moveon.search.byfilter.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.paging.PagingData
import com.moveon.common.mapper.CountriesMapper
import com.moveon.core.fetcher.fetchData
import com.moveon.core.viewmodel.CoreViewModel
import com.moveon.network.network.data.CountriesResponse
import com.moveon.pagination.fetcher.fetchPagingData
import com.moveon.search.byfilter.data.respository.SearchByFilterRepository
import com.moveon.search.byfilter.presentation.bottomsheet.container.SearchByFilterBottomSheetContainer
import com.moveon.search.byfilter.presentation.data.SearchByFilterItemPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

@HiltViewModel
class SearchByFilterViewModel @Inject constructor(
    repository: SearchByFilterRepository,
    private val countriesMapper: CountriesMapper
) : CoreViewModel() {

    var searchPaging: Flow<PagingData<SearchByFilterItemPresentation>> = emptyFlow()
    var isSearchBottomSheetVisible: Boolean by mutableStateOf(false)
    var isProgressVisible: Boolean by mutableStateOf(false)
    var isCountriesErrorVisible: Boolean by mutableStateOf(false)

    val searchByFilterBottomSheetContainer: SearchByFilterBottomSheetContainer =
        SearchByFilterBottomSheetContainer(onHideBottomSheet = {
            isSearchBottomSheetVisible = false
        }, onBtnClicked = {
            fetchSearchData()
        })

    private val searchFilterPaging = fetchPagingData(
        repository = repository,
        onSuccess = { isProgressVisible = false },
        onError = { isProgressVisible = false }
    )

    private val fetchCountries = fetchData(
        repository = repository,
        callFunction = SearchByFilterRepository::getCountries,
        onSuccess = { response ->
            response?.let {
                handleSuccessCountriesResponse(it)
            }
        },
        onError = { handleCountriesError() }
    )

    fun fetchCountries() {
        isProgressVisible = true
        fetchCountries.fetch()
    }

    private fun handleCountriesError() {
        isProgressVisible = false
    }

    private fun handleSuccessCountriesResponse(response: CountriesResponse) {
        searchByFilterBottomSheetContainer.setNewCountries(countriesMapper.map(response))
        fetchSearchData()
    }

    private fun fetchSearchData() {
        isProgressVisible = true
        searchPaging =
            searchFilterPaging.fetch(searchByFilterBottomSheetContainer.createSearchRequest())
    }

    fun onOpenBottomSheetClicked() {
        isSearchBottomSheetVisible = true
    }
}