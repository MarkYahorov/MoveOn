package com.moveon.core.navigation

import androidx.navigation.NavHostController

fun NavHostController.setArgs(args: NavigationArgs) {
    currentBackStackEntry?.savedStateHandle?.set("", args)
}