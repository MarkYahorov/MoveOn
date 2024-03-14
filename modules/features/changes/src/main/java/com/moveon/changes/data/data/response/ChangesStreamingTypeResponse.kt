package com.moveon.changes.data.data.response

import com.google.gson.annotations.SerializedName

enum class ChangesStreamingTypeResponse {
    @SerializedName("free")
    Free,

    @SerializedName("subscription")
    Subscription,

    @SerializedName("buy")
    Buy,

    @SerializedName("rent")
    Rent,

    @SerializedName("addon")
    Addon
}