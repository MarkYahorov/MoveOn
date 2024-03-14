package com.moveon.network.network.data

import com.moveon.core.ResponseError

data class NetworkError(
    override val title: String,
    override val detail: String?,
    val httpCode: HttpCode
) : ResponseError