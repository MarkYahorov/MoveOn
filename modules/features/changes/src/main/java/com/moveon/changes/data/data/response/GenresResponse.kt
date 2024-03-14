package com.moveon.changes.data.data.response

import com.google.gson.annotations.SerializedName

data class GenresResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)