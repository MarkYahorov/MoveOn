package com.moveon.core.fetcher

interface Fetcher<Input> {

    fun fetch()

    fun fetch(input: Input)
}