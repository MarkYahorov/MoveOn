package com.moveon.common.data

import com.google.gson.annotations.SerializedName

enum class ShowTypeResponse {

    @SerializedName("movie")
    Movie,

    @SerializedName("series")
    Series
}