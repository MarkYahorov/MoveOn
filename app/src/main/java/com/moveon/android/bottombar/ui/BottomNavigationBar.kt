package com.moveon.android.bottombar.ui

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.moveon.android.bottombar.container.BottomBarDataContainer

@Composable
fun BottomNavigationBar(container: BottomBarDataContainer) {
    BottomNavigation(
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
    ) {
        container.items.forEach {
            BottomNavigationItem(
                selected = container.isItemSelected(it),
                onClick = { container.onItemClicked(it) },
                icon = {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = null,
                        tint = if (container.isItemSelected(it)) Color.White else Color.Black,
                        modifier = Modifier.size(30.dp)
                    )
                },
                modifier = Modifier.fillMaxHeight(),
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.White
            )
        }
    }
}