package com.diavolo.githubuserfinder.presentation.navigation

sealed class Screen(val route: String) {
    object Search : Screen("search")
    object UserDetail : Screen("user_detail/{username}") {
        fun createRoute(username: String) = "user_detail/$username"
    }
}
