package com.diavolo.githubuserfinder.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.diavolo.githubuserfinder.presentation.screen.SearchScreen
import com.diavolo.githubuserfinder.presentation.screen.UserDetailScreen

@Composable
fun GitHubUserFinderNavigation(
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Search.route
    ) {
        composable(Screen.Search.route) {
            SearchScreen(
                onUserClick = { username ->
                    navController.navigate(Screen.UserDetail.createRoute(username))
                }
            )
        }
        
        composable(Screen.UserDetail.route) { backStackEntry ->
            val username = backStackEntry.arguments?.getString("username") ?: ""
            UserDetailScreen(
                username = username,
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}
