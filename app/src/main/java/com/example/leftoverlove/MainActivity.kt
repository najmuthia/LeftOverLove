@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class,
    ExperimentalMaterial3Api::class
)

package com.example.leftoverlove

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.leftoverlove.ui.theme.LeftOverLoveTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LeftOverLoveTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "signup") {
        composable("login") { LoginScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("profile") { ProfilePage(navController) }
        composable("editProfile") { EditProfilePage(navController) }
        composable("home") { HomeScreen(navController = navController) }
        composable("activity") { ActivityScreen(navController = navController) }
        composable("aktivitasku") { AktivitasScreen(navController) }
        composable("donasiku") { DonasikuScreen(navController) }
        composable("favoritku") { FavoritkuScreen(navController) }
    }
}

@Composable
fun DonasikuScreen(navController: NavController? = null) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Riwayat Donasi",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(0xFFF9F9F9))
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9F9F9))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(donationHistory) { donation ->
                    DonationCard(donation = donation)
                }
            }
        }
    }
}

@Composable
fun DonationCard(donation: Donation) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = donation.date,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
                Text(
                    text = "+ ${donation.points} poin",
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = donation.title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = donation.status,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color(0xFF00A651))
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Rp ${donation.amount}",
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                )
                Text(
                    text = donation.paymentMethod,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                )
            }
        }
    }
}

data class Donation(
    val date: String,
    val points: Int,
    val title: String,
    val status: String,
    val amount: String,
    val paymentMethod: String
)

val donationHistory = listOf(
    Donation(
        date = "20 Oktober 2024",
        points = 100,
        title = "Donasi",
        status = "Selesai",
        amount = "10.000",
        paymentMethod = "Gopay"
    ),
    Donation(
        date = "7 Maret 2024",
        points = 200,
        title = "Donasi",
        status = "Selesai",
        amount = "20.000",
        paymentMethod = "Gopay"
    )
)

@Composable
fun FavoritkuScreen(navController: NavController? = null) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Favoritku",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController?.navigateUp() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Kembali")
                    }
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = Color(0xFFF9F9F9)
                )
            )
        }
    ) { innerPadding ->
    Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF9F9F9))
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(favoriteList) { favorite ->
                    FavoriteCard(favorite = favorite)
                }
            }
        }
    }
}

@Composable
fun FavoriteCard(favorite: Favorite) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(4.dp, shape = RoundedCornerShape(8.dp)),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.login1),
                    contentDescription = "Food Image",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
                IconButton(onClick = { /* Handle favorite toggle */ }) {
                    Icon(
                        imageVector = if (favorite.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (favorite.isFavorite) Color.Red else Color.Gray
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = favorite.name,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = favorite.address,
                style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Rp ${favorite.price}",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
            )
        }
    }
}

data class Favorite(
    val name: String,
    val address: String,
    val price: String,
    val imageUrl: String,
    val isFavorite: Boolean
)

val favoriteList = listOf(
    Favorite(
        name = "Nasi Goreng 88",
        address = "Jalan Soekarno Hatta Nomor 97",
        price = "10.000",
        imageUrl = "https://via.placeholder.com/80", // Ganti dengan URL gambar makanan
        isFavorite = true
    )
)


@Composable
fun AktivitasScreen(navController: NavController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Aktivitas",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 22.dp)
        )
        TabRow(
            selectedTabIndex = selectedTabIndex,
            indicator = { tabPositions ->
                Box(
                    modifier = Modifier
                        .tabIndicatorOffset(tabPositions[selectedTabIndex])
                        .height(2.dp)
                        .background(Color(0xFF92C947))
                )
            }
        ) {
            Tab(
                text = { Text("Dalam Proses") },
                selected = selectedTabIndex == 0,
                onClick = { selectedTabIndex = 0 }
            )
            Tab(
                text = { Text("Riwayat") },
                selected = selectedTabIndex == 1,
                onClick = { selectedTabIndex = 1 }
            )
        }

        when (selectedTabIndex) {
            0 -> DalamProsesScreen(navController) // Kirim NavController
            1 -> RiwayatScreen(navController)
        }
    }
}


@Composable
fun RiwayatScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Riwayat Pemesanan",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 22.dp)
        )

        // Riwayat Pesanan (Dummy Data)
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Gambar di sebelah kiri
                Image(
                    painter = painterResource(id = R.drawable.nasi_goreng), // Ganti dengan gambar riwayat
                    contentDescription = "Nasi Goreng",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                )

                // Kolom untuk teks di sebelah kanan gambar
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Nama makanan
                    Text(
                        text = "Nasi Goreng 88",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    // Harga makanan
                    Text(
                        text = "Rp 10.000",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    // Status pesanan
                    Text(
                        text = "Selesai",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF92C947)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))
        FooterNavBar(navController)

    }
}


@Composable
fun DalamProsesScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Hari Ini",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 18.dp)
        )

        // Card untuk efek shadow
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F7F7)), // Latar belakang terang
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable { // Navigasi ke halaman PesananDiantar
                    navController.navigate("pesanan_diantar")
                }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // Gambar di sebelah kiri
                Image(
                    painter = painterResource(id = R.drawable.nasi_goreng),
                    contentDescription = "Nasi Goreng",
                    modifier = Modifier
                        .size(80.dp)
                        .padding(end = 16.dp)
                )

                // Kolom untuk teks di sebelah kanan gambar
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    // Nama makanan
                    Text(
                        text = "Nasi Goreng 88",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    // Deskripsi pesanan
                    Text(
                        text = "1 Nasi Goreng Spesial",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.DarkGray,
                        modifier = Modifier.padding(bottom = 10.dp)
                    )
                    // Status pengiriman
                    Text(
                        text = "Makanan sedang diantar",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF92C947)
                    )
                }

                // Harga di pojok kanan atas
                Text(
                    text = "Rp 10.000",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.Top)
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
//        Button(
//            onClick = { /* Implementasi untuk melihat riwayat */ },
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(48.dp),
//            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF92C947))
//        ) {
//            Text("Lihat Riwayat Pemesanan")
//        }
        Spacer(modifier = Modifier.weight(1f))

        // Footer Navigasi
        FooterNavBar(navController)
    }
}

@Composable
fun FooterNavBar(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White) // Latar belakang putih untuk footer
    ) {
        // Garis Pembatas
        Divider(
            color = Color.LightGray,
            thickness = 1.dp,
            modifier = Modifier.fillMaxWidth()
        )

        // Row untuk ikon navigasi
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp), // Margin atas dan bawah
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly // Membagi ruang sama rata
        ) {
            // Navigasi Aktivitas
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { navController.navigate("aktivitas_screen") }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_aktivitas),
                    contentDescription = "Aktivitas",
                    tint = Color(0xFF92C947),
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Aktivitas",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            // Navigasi Beranda
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { navController.navigate("beranda_screen") }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.beranda_icon),
                    contentDescription = "Beranda",
                    tint = Color(0xFF92C947),
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Beranda",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }

            // Navigasi Profil
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.clickable { navController.navigate("profile") }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.profil_icon),
                    contentDescription = "Profil",
                    tint = Color(0xFF92C947),
                    modifier = Modifier.size(32.dp)
                )
                Text(
                    text = "Profil",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun PreviewDalamProsesScreen() {
//        DalamProsesScreen()
//
//}

@Preview(showBackground = true)
@Composable
fun PreviewAktivitasScreen() {
    val mockNavController = rememberNavController() // Dummy NavController
    AktivitasScreen(navController = mockNavController)
}

@Preview(showBackground = true)
@Composable
fun PreviewRiwayatScreen() {
    RiwayatScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LeftOverLoveTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfilePreview() {
    LeftOverLoveTheme {
        ProfilePage()
    }
}

@Preview(showBackground = true)
@Composable
fun EditProfilePagePreview() {
    LeftOverLoveTheme {
        EditProfilePage()
    }
}

@Preview(showBackground = true)
@Composable
fun DonasikuScreenPreview() {
    LeftOverLoveTheme {
        DonasikuScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun FavoritkuScreenPreview() {
    LeftOverLoveTheme {
        FavoritkuScreen()
    }
}
