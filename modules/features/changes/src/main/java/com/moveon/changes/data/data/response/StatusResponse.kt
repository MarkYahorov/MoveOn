package com.moveon.changes.data.data.response

import com.google.gson.annotations.SerializedName

enum class StatusResponse {
    @SerializedName("Returning Series")
    Returning,

    @SerializedName("Planned")
    Planned,

    @SerializedName("In Production")
    InProduction,

    @SerializedName("Ended")
    Ended,

    @SerializedName("Cancelled")
    Cancelled,

    @SerializedName("Pilot")
    Pilot
}