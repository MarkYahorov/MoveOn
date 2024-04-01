package com.moveon.ui_core.bottombar.container

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.moveon.ui_core.bottombar.data.BottomBarItems

class BottomBarDataContainer {

    private var selectedItem: BottomBarItems by mutableStateOf(BottomBarItems.values().first())
    fun isItemSelected(bottomBarItem: BottomBarItems): Boolean {
        return bottomBarItem == selectedItem
    }

    fun onItemClicked(clickedItem: BottomBarItems) {
        if (!isItemSelected(clickedItem)) {
            selectedItem = clickedItem
        }
    }

    val items: List<BottomBarItems> = BottomBarItems.values().toList()
}