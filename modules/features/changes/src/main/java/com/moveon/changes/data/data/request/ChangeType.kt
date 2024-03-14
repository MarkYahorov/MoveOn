package com.moveon.changes.data.data.request

import com.google.gson.annotations.SerializedName

enum class ChangeType {
    @SerializedName("new")
    New,
    @SerializedName("removed")
    Removed,
    @SerializedName("updated")
    Updated
}