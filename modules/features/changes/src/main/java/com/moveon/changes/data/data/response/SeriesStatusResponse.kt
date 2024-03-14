package com.moveon.changes.data.data.response

import com.google.gson.annotations.SerializedName

data class SeriesStatusResponse(
    @SerializedName("statusText")
    val status: StatusResponse
)
