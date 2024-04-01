package com.moveon.ui_core.bottombar.data

import androidx.annotation.DrawableRes
import com.moveon.ui_core.R

enum class BottomBarItems(@DrawableRes val icon: Int) {
    Changes(R.drawable.changes_bottom_icon),
    Search(R.drawable.search_bottom_icon);
}