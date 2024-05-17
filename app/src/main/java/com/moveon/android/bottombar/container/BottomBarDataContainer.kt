package com.moveon.android.bottombar.container

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moveon.android.bottombar.data.BottomBarItems
import com.moveon.core.navigation.BaseNavigationController

class BottomBarDataContainer(private val navController: BaseNavigationController) {

    private var selectedItem: BottomBarItems by mutableStateOf(BottomBarItems.values().first())
    fun isItemSelected(bottomBarItem: BottomBarItems): Boolean {
        return bottomBarItem == selectedItem
    }

    fun onItemClicked(clickedItem: BottomBarItems) {
        if (!isItemSelected(clickedItem)) {
            selectedItem = clickedItem
            navController.navigateToRouteWithoutArgs(clickedItem.route)
        }

    }

    val items: List<BottomBarItems> = BottomBarItems.values().toList()
}