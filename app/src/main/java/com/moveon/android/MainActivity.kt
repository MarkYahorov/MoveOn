package com.moveon.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.moveon.android.ui.theme.MoveOnTheme
import com.moveon.changes.presentation.ui.ChangesPage
import com.moveon.ui_core.bottombar.container.BottomBarDataContainer
import com.moveon.ui_core.bottombar.ui.BottomNavigationBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoveOnTheme {
                // A surface container using the 'background' color from the theme
                val navController = rememberNavController()
                val bottomBarContainer = remember { BottomBarDataContainer() }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        BottomNavigationBar(container = bottomBarContainer)
                    }) {
                        Box(modifier = Modifier.padding(it)) {
                            NavHost(
                                navController = navController,
                                startDestination = "changes",
                                route = "start"
                            ) {
                                composable(
                                    "changes"
                                ) {
                                    ChangesPage()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}