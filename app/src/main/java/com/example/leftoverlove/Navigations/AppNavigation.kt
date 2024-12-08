package com.example.leftoverlove.Navigations

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.leftoverlove.Activity.ActivityScreen
import com.example.leftoverlove.Home.ui.HomeScreen
import com.example.leftoverlove.Profile.ProfileScreen


@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = NavigationRoute.Home.route
    ) {
        // Route untuk HomeScreen
        composable(NavigationRoute.Home.route) {
            HomeScreen(navController)
        }

        // Route untuk ActivityScreen (Aktivitas)
        composable(NavigationRoute.Activity.route) {
            ActivityScreen(navController)
        }

        // Route untuk ProfileScreen
        composable(NavigationRoute.Profile.route) {
            ProfileScreen(navController)
        }

    }
}
