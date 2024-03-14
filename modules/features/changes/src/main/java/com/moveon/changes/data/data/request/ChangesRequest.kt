package com.moveon.changes.data.data.request

import com.google.gson.annotations.SerializedName
import com.moveon.pagination.data.PagingRequest

data class ChangesRequest(
    @SerializedName("change_type")
    val changeType: ChangeType,
    @SerializedName("services")
    val service: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("target_type")
    val targetType: TargetType
) : PagingRequest