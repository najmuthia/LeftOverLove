package com.example.leftoverlove.Home.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import com.example.leftoverlove.BottomNavBar
import com.example.leftoverlove.Home.ui.theme.LeftOverLoveTheme
import com.example.leftoverlove.R
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.leftoverlove.DetailMakanan.DetailMakanan

@Composable
fun AppNavigation(navController: NavController){
    // Menyiapkan NavController
    val navController = rememberNavController()

    // NavHost untuk menentukan rute-rute
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController) // Pass navController ke HomeScreen
        }
        composable("detailMakanan") {
            DetailMakanan(navController) // Halaman DetailMakanan
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center // This centers the title
                    ) {
                        Text(
                            text = "Beranda",
                            style = MaterialTheme.typography.titleLarge
                        )
                    }
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
            // val navController = rememberNavController()

            BottomNavigationBar(navController = navController)
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
            Banner(
                onClick = { /* Navigasikan ke halaman Donasi */ }
            )

            // Rekomendasi Makanan
            SectionTitle(title = "Rekomendasi Makanan")
            FoodRecommendationList(navController)

            // Restoran Terdekat
            SectionTitle(title = "Restoran Terdekat")
            NearbyRestaurantsList(navController)
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
            containerColor = Color(0xFFEBEBEB),  // Set background to light grey
            focusedIndicatorColor = Color(0xFFEBEBEB),  // Set border when focused to the same color
            unfocusedIndicatorColor = Color(0xFFEBEBEB),  // Set border when unfocused to the same color
            disabledIndicatorColor = Color(0xFFEBEBEB)  // Set border when disabled to the same color
        )
    )
}

// Banner
@Composable
fun Banner(onClick: () -> Unit) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth() // Memastikan gambar selebar layar
//            .height(200.dp) // Atur tinggi gambar sesuai kebutuhan
//            .clip(RoundedCornerShape(12.dp)) // Membuat sudut membulat
//            .background(MaterialTheme.colorScheme.surface) // Warna latar belakang (opsional)
//    ) {
        Image(
            painter = painterResource(id = R.drawable.bannerdonasi), // Ganti dengan gambar yang kamu inginkan
            contentDescription = "Gambar Banner",
            //contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth() // Memastikan gambar selebar layar
                .height(200.dp) // Atur tinggi gambar sesuai kebutuhan
                .clip(RoundedCornerShape(12.dp))
                .padding(start = 10.dp, end = 10.dp)
        )
    }


// FoodRecommendationList
@Composable
fun FoodRecommendationList(navController: NavController) {
    LazyRow(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(top = 0.dp)
    ) {
        items(2) {
            FoodCard(navController)
        }
    }
}


// Section Title
@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

@Composable
fun FoodCard(navController: NavController) {
    Card(
        modifier = Modifier
            .size(180.dp)
            .padding(end = 8.dp)
            .clickable {
                // Navigasi ke DetailMakanan screen saat card di-klik
                navController.navigate("detailMakanan")
            },
        colors = CardDefaults.cardColors(containerColor = Color.White), // Latar belakang putih
        shape = RoundedCornerShape(8.dp), // Sudut rounded
        elevation = CardDefaults.cardElevation(8.dp) // Bayangan halus
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
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
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
fun NearbyRestaurantsList(navController: NavController) {
    LazyRow(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        items(3) { // Sesuaikan jumlah restoran
            RestaurantCard(navController)
        }
    }
}

@Composable
fun RestaurantCard(navController: NavController) {
    Card(
        modifier = Modifier
            .width(150.dp)
            .height(160.dp)
            .padding(end = 8.dp)
            .clickable {
                // Navigasi ke DetailMakanan screen saat card di-klik
                navController.navigate("detailMakananPesan")
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
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
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.Bold),
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
fun BottomNavigationBar(navController: NavController) {
    NavigationBar(
        modifier = Modifier.height(64.dp),
        containerColor = Color(0xFFF9F9F9)// Menetapkan tinggi dari NavigationBar
    ) {
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("Activity") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.aktivitas), // Ikon custom
                    contentDescription = "Aktivitas",
                    tint = Color(0xFF7B886F)
                )
            },
            label = {
                Text(
                text = "Aktivitas",
                color = Color(0xFF7B886F) // Warna teks untuk "Aktivitas"
            )
            }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("HomeScreen") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.beranda), // Ikon custom
                    contentDescription = "beranda",
                    tint = Color(0xFF7B886F)
                )
            },
            label = {
                Text(
                text = "Beranda",
                color = Color(0xFF7B886F) // Warna teks untuk "Aktivitas"
            )  }
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("Profile") },
            icon = {
                Icon(
                    painter = painterResource(id = R.drawable.profil), // Ikon custom
                    contentDescription = "Profil",
                    tint = Color(0xFF7B886F)
                )
            },
            label = {
                Text(
                text = "Profil",
                color = Color(0xFF7B886F) // Warna teks untuk "Aktivitas"
            )  }
        )
    }
}





@Preview(showBackground = true)
@Composable
fun GreetingPreview3() {
    LeftOverLoveTheme {
        val navController = rememberNavController()
        HomeScreen(navController = navController)

        AppNavigation(navController = navController)

    }
}