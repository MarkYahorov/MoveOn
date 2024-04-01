package com.moveon.android

import android.content.Context
import androidx.navigation.NavHostController
import com.moveon.core.navigation.BaseNavigationController
import dagger.Provides
import dagger.hilt.android.qualifiers.ActivityContext

object NavigationModule {

    @Provides
    fun provideNavHostController(@ActivityContext context: Context): NavHostController {
        return NavHostController(context)
    }

    @Provides
    fun provideBaseNavigationController(
        navHostController: NavHostController
    ): BaseNavigationController {
        return BaseNavigationController(navHostController)
    }
}