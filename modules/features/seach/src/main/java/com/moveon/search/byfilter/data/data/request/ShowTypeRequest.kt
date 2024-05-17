package com.moveon.search.byfilter.data.data.request

import com.google.gson.annotations.SerializedName

enum class ShowTypeRequest {

    @SerializedName("all")
    All,

    @SerializedName("movie")
    Movie,

    @SerializedName("series")
    Series
}