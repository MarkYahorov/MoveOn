package com.moveon.core.navigation

import androidx.navigation.NavHostController
import com.moveon.core.MainRoutes
import javax.inject.Inject

class BaseNavigationController @Inject constructor(val navHostController: NavHostController) {

    fun navigateToDetail(imdbId: String?, tmdbId: Int?) {

        navHostController.navigate(
            when {
                imdbId != null && tmdbId != null -> {
                    MainRoutes.Detail.navigationWithArgsRoute + "/$imdbId/$tmdbId"
                }

                imdbId != null -> {
                    MainRoutes.Detail.navigationWithArgsRoute + "/$imdbId"
                }

                tmdbId != null -> {
                    MainRoutes.Detail.navigationWithArgsRoute + "/$tmdbId"
                }

                else -> MainRoutes.Detail.navigationWithArgsRoute
            }
        )
    }

    fun navigateToRouteWithoutArgs(route: String) {
        navHostController.navigate(route)
    }
}