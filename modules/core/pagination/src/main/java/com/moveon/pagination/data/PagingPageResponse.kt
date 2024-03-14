package com.moveon.pagination.data

import com.google.gson.annotations.SerializedName

data class PagingPageResponse<T: Any>(
    @SerializedName("hasMore")
    val hasKey: Boolean,
    @SerializedName("nextCursor")
    val nextKey: String,
    @SerializedName("result")
    val data: List<T>
)