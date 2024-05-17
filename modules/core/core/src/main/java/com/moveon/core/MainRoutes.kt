package com.moveon.core

enum class MainRoutes(val route: String, val navigationWithArgsRoute: String = route) {

    Changes("changes_route"),
    Search("search_route"),
    Detail("detail/{imdbId}/{tmdbId}", "detail")
}