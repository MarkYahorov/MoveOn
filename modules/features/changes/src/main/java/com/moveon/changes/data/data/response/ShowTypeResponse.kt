package com.moveon.changes.data.data.response

import com.google.gson.annotations.SerializedName

enum class ShowTypeResponse {

    @SerializedName("movie")
    Movie,

    @SerializedName("series")
    Series
}