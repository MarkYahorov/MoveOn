package com.moveon.changes.presentation.data

import com.moveon.changes.data.data.request.ChangeType
import com.moveon.ui_core.data.DropDownItem

enum class ChangeTypePresentation(override val title: String) : DropDownItem {
    New("New"),
    Removed("Removed"),
    Updated("Updated")
}

internal fun ChangeTypePresentation.mapToRequest(): ChangeType {
    return when (this) {
        ChangeTypePresentation.New -> ChangeType.New
        ChangeTypePresentation.Removed -> ChangeType.Removed
        ChangeTypePresentation.Updated -> ChangeType.Updated
    }
}