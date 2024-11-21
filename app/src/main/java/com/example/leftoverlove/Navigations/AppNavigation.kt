package com.example.leftoverlove.Navigations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.leftoverlove.Navigations.ui.theme.LeftOverLoveTheme

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.leftoverlove.ui.theme.Activity.ActivityScreen
import com.example.leftoverlove.ui.theme.Home.HomeScreen
import com.example.leftoverlove.ui.theme.Profile.ProfileScreen


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
        composable(NavigationRoute.Order.route) {
            ActivityScreen(navController)
        }

        // Route untuk ProfileScreen
        composable(NavigationRoute.Profile.route) {
            ProfileScreen(navController)
        }
    }
}
