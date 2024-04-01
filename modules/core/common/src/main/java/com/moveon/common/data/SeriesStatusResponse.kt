package com.moveon.common.data

import com.google.gson.annotations.SerializedName

data class SeriesStatusResponse(
    @SerializedName("statusText")
    val status: StatusResponse
)
