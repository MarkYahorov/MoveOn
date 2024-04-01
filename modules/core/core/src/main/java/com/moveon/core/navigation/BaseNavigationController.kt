package com.moveon.core.navigation

import androidx.navigation.NavHostController
import javax.inject.Inject

class BaseNavigationController @Inject constructor(private val navHostController: NavHostController) {

    fun navigate(route: String, args: NavigationArgs? = null) {
        if (navHostController.currentDestination?.route != route) {
            navHostController.apply {
                navigate(route)

                if (args != null) {
                    setArgs(args)
                }
            }
        }
    }
}