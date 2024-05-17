package com.moveon.search.byfilter.presentation.data

import com.moveon.search.byfilter.data.data.request.ShowTypeRequest
import com.moveon.ui_core.data.DropDownItem

enum class SearchByFilterType(override val title: String) : DropDownItem {
    All("All"), Movie("Movie"), Series("Series")
}

fun SearchByFilterType.toRequest(): ShowTypeRequest {
    return when(this){
        SearchByFilterType.All -> ShowTypeRequest.All
        SearchByFilterType.Series -> ShowTypeRequest.Series
        SearchByFilterType.Movie -> ShowTypeRequest.Movie
    }
}