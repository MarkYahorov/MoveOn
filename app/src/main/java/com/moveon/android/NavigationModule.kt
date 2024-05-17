package com.moveon.android

import android.content.Context
import androidx.navigation.NavHostController
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import com.moveon.core.navigation.BaseNavigationController
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext

@Module
@InstallIn(ActivityComponent::class)
object NavigationModule {

    @Provides
    fun provideNavHostController(@ActivityContext context: Context): NavHostController {
        return NavHostController(context).apply {
            this.navigatorProvider.addNavigator(ComposeNavigator())
            this.navigatorProvider.addNavigator(DialogNavigator())
        }
    }

    @Provides
    fun provideBaseNavigationController(
        navHostController: NavHostController
    ): BaseNavigationController {
        return BaseNavigationController(navHostController)
    }
}