package com.example.leftoverlove.ui.theme.Home

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
import com.example.leftoverlove.ui.theme.Home.ui.theme.LeftOverLoveTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.leftoverlove.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Beranda",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                actions = {
                    IconButton(onClick = { /* TODO: Tambahkan aksi */ }) {
                        Icon(
                            imageVector = Icons.Default.FavoriteBorder,
                            contentDescription = "Favorite",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors( // Gunakan `colors`
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            BottomNavigationBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Search Bar
            SearchBar()

            // Banner
            //   Banner()

            // Rekomendasi Makanan
            SectionTitle(title = "Rekomendasi Makanan")
            FoodRecommendationList()

            // Restoran Terdekat
            SectionTitle(title = "Restoran Terdekat")
            NearbyRestaurantsList()
        }
    }
}

// Search Bar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    TextField(
        value = "",
        onValueChange = { /* TODO: Tambahkan logika pencarian */ },
        placeholder = { Text(text = "Mau cari apa kawan?") },
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = "Search"
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    )
}

// Section Title
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

// Rekomendasi Makanan
@Composable
fun FoodRecommendationList() {
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items(2) { // Sesuaikan jumlah item
            FoodCard()
        }
    }
}

@Composable
fun FoodCard() {
    Card(
        modifier = Modifier
            .size(150.dp)
            .padding(end = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.nasi_goreng_88), // Ganti dengan gambar Anda
                contentDescription = "Gambar Makanan",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(100.dp)
            )
            Text(
                text = "Nasi Goreng Spesial",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Rp 10.000",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

// Restoran Terdekat
@Composable
fun NearbyRestaurantsList() {
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items(3) { // Sesuaikan jumlah restoran
            RestaurantCard()
        }
    }
}

@Composable
fun RestaurantCard() {
    Card(
        modifier = Modifier
            .width(120.dp)
            .padding(end = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.nasi_goreng_88), // Ganti gambar restoran
                contentDescription = "Gambar Restoran",
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(80.dp)
            )
            Text(
                text = "Restoran B88",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = "Suhat - Malang",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
        }
    }
}

// Bottom Navigation Bar
@Composable
fun BottomNavigationBar() {
    NavigationBar {
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: Tambahkan aksi */ },
            icon = {
                Icon(Icons.Default.List, contentDescription = "Aktivitas")
            },
            label = { Text("Aktivitas") }
        )
        NavigationBarItem(
            selected = true,
            onClick = { /* TODO: Tambahkan aksi */ },
            icon = {
                Icon(Icons.Default.Home, contentDescription = "Beranda")
            },
            label = { Text("Beranda") }
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO: Tambahkan aksi */ },
            icon = {
                Icon(Icons.Default.Person, contentDescription = "Profil")
            },
            label = { Text("Profil") }
        )
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    LeftOverLoveTheme{
        HomeScreen()
    }
}