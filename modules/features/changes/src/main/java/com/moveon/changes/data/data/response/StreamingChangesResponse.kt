package com.moveon.changes.data.data.response

import com.google.gson.annotations.SerializedName

data class StreamingChangesResponse(
    @SerializedName("service")
    val service: String,
    @SerializedName("streamingType")
    val streamingType: ChangesStreamingTypeResponse,
    @SerializedName("time")
    val time: Int
)
