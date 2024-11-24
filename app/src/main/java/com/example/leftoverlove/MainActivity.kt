package com.example.leftoverlove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leftoverlove.Activity.ActivityScreen
import com.example.leftoverlove.Home.HomeScreen
import com.example.leftoverlove.ui.theme.LeftOverLoveTheme
//
//class MainActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//        setContent {
//            LeftOverLoveTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MainScreen(
//
//                    )
//                }
//            }
//        }
//    }
//}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) } // BottomNavBar digunakan di sini
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "home", // Screen awal
            modifier = Modifier.padding(innerPadding)
        ) {
            composable("home") { HomeScreen(navController = navController) }
            composable("activity") { ActivityScreen(navController = navController) }
            //composable("profile") { ProfileScreen(navController = navController) }
        }
    }
}