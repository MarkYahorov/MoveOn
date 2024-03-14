package com.moveon.changes.data.data.request

import com.google.gson.annotations.SerializedName

enum class TargetType {
    @SerializedName("show")
    Show,
     @SerializedName("movie")
    Movie,
     @SerializedName("series")
    Series,
     @SerializedName("season")
    Season,
     @SerializedName("episode")
    Episode
}