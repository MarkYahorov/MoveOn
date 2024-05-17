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
import androidx.compose.material3.Text
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.moveon.android.bottombar.container.BottomBarDataContainer
import com.moveon.android.bottombar.ui.BottomNavigationBar
import com.moveon.android.ui.theme.MoveOnTheme
import com.moveon.changes.presentation.ui.ChangesPage
import com.moveon.core.MainRoutes
import com.moveon.core.navigation.BaseNavigationController
import com.moveon.detail.presentation.view.DetailScreen
import com.moveon.search.byfilter.presentation.view.SearchByFilterView
import com.moveon.search.bytitle.presentation.view.SearchByTitleView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var baseNavController: BaseNavigationController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MoveOnTheme {
                // A surface container using the 'background' color from the theme
                val bottomBarContainer = remember { BottomBarDataContainer(baseNavController) }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(bottomBar = {
                        BottomNavigationBar(container = bottomBarContainer)
                    }) {
                        Box(modifier = Modifier.padding(it)) {
                            NavHost(
                                navController = baseNavController.navHostController,
                                startDestination = MainRoutes.Changes.route,
                                route = "start"
                            ) {
                                composable(
                                    MainRoutes.Changes.route
                                ) {
                                    ChangesPage(navigationController = baseNavController)
                                }
                                composable(
                                    route = MainRoutes.Search.route
                                ) {
                                    SearchByFilterView(baseNavigationController = baseNavController)
                                }
                                composable(
                                    route = MainRoutes.Detail.route,
                                    arguments = listOf(
                                        navArgument("imdbId") {
                                            type = NavType.StringType
                                            nullable = true
                                            defaultValue = null
                                        },
                                        navArgument("tmdbId") {
                                            type = NavType.IntType
                                        }
                                    )
                                ) { backstackEntry ->
                                    DetailScreen(
                                        imbdId = backstackEntry.arguments?.getString("imdbId"),
                                        tmdbId = backstackEntry.arguments?.getInt("tmdbId")
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}