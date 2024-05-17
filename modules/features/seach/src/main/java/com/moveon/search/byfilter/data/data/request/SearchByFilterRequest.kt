package com.moveon.search.byfilter.data.data.request

import com.google.gson.annotations.SerializedName
import com.moveon.pagination.data.PagingRequest

class SearchByFilterRequest(
    @SerializedName("service")
    val service: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("show_type")
    val showType: ShowTypeRequest,
    @SerializedName("keyword")
    val keyword: String?
): PagingRequest