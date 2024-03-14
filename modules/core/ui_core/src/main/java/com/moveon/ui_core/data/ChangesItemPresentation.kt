package com.moveon.ui_core.data

data class ChangesItemPresentation(
    val changes: List<StreamingChangesPresentation>,
    val show: ChangesShowPresentation
)

data class StreamingChangesPresentation(
    val service: String,
    val streamingType: ChangesStreamingTypePresentation,
    val time: String
)