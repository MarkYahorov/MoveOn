package com.moveon.detail.data.response

import com.google.gson.annotations.SerializedName

data class DetailMainResponse(
    @SerializedName("result")
    val detail: DetailResponse
)
