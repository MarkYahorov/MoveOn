package com.moveon.changes.data.data.response

import com.google.gson.annotations.SerializedName

data class ChangeItemResponse(
    @SerializedName("changes")
    val changes: List<StreamingChangesResponse>,
    @SerializedName("show")
    val show: ChangesShowResponse
)
