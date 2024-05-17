package com.moveon.android.bottombar.data

import androidx.annotation.DrawableRes
import com.moveon.core.MainRoutes
import com.moveon.ui_core.R

enum class BottomBarItems(@DrawableRes val icon: Int, val route: String) {

    Changes(R.drawable.changes_bottom_icon, MainRoutes.Changes.route),
    Search(R.drawable.search_bottom_icon, MainRoutes.Search.route);
}