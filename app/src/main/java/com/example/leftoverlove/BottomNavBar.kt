package com.example.leftoverlove

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController

import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.res.painterResource

@Composable
fun BottomNavBar(navController: NavController) {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("activity") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.aktivitas), // Ikon custom
                    contentDescription = "Aktivitas",
                    tint = Color(0xFF7B886F)
                )
            },
            label = { Text("Aktivitas") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* Aksi ketika "Beranda" diklik */ },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.beranda), // Ikon custom
                    contentDescription = "Beranda",
                    tint = Color(0xFF7B886F)
                )
            },
            label = { Text("Beranda") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("profile") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.profil), // Ikon custom
                    contentDescription = "Profil",
                    tint = Color(0xFF7B886F)
                )
            },
            label = { Text("Profil") }
        )
    }
}


//data class BottomNavItem(val title: String, val route: String, val icon: ImageVector)

@Preview(showBackground = true)
@Composable
fun BottomNavigationBarPreview() {
    val fakeNavController = rememberNavController()
    BottomNavBar(navController = fakeNavController)
}



