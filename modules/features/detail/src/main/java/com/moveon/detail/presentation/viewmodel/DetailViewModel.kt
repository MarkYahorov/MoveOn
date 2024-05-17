package com.moveon.detail.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moveon.core.fetcher.fetchData
import com.moveon.core.viewmodel.CoreViewModel
import com.moveon.detail.data.repository.DetailsRepository
import com.moveon.detail.data.request.DetailRequest
import com.moveon.detail.presentation.data.DetailPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    repository: DetailsRepository
) : CoreViewModel() {

    var isSeasonsVisible: Boolean by mutableStateOf(false)
    var imdbId: String? by mutableStateOf("")
    var tmdbId: Int? by mutableStateOf(null)
    var isProgressVisible: Boolean by mutableStateOf(false)
    var detail: DetailPresentation? by mutableStateOf(null)
    var isErrorVisible: Boolean by mutableStateOf(false)
    var error: String by mutableStateOf("")

    private val fetchDetailInfo = fetchData(
        repository = repository,
        callFunction = DetailsRepository::fetchDetail,
        onSuccess = {
            detail = it
            isProgressVisible = false
        },
        onError = {
            isProgressVisible = false
            isErrorVisible = true
            error = it?.detail.orEmpty()
        }
    )

    fun updateRequestIds(imbdId: String?, tmdbId: Int?) {
        this.imdbId = imbdId
        this.tmdbId = tmdbId

        fetchDetailInfo()
    }

    private fun fetchDetailInfo() {
        isProgressVisible = true
        fetchDetailInfo.fetch(DetailRequest(imdbId, tmdbId))
    }

    fun showSeasons() {
        isSeasonsVisible = true
    }

    fun hideSeasons() {
        isSeasonsVisible = false
    }
}