package com.example.papbroom.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papbroom.R
import com.example.papbroom.ui.theme.PAPBRoomTheme

@Composable
fun AktivitasScreen() {
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
        // TabRow untuk memilih antara Riwayat dan Dalam Proses
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

        // Menampilkan konten berdasarkan tab yang dipilih
        when (selectedTabIndex) {
            0 -> DalamProsesScreen()  // Menampilkan halaman "Dalam Proses"
            1 -> RiwayatScreen()      // Menampilkan halaman "Riwayat"
        }
    }
}

@Composable
fun DalamProsesScreen() {
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
                        .size(100.dp)
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

        // Footer Navigasi dengan Card
        Column(
            modifier = Modifier
                .fillMaxWidth() // Membuat Column mengisi seluruh lebar layar
                .background(Color.White) // Pastikan latar belakang footer putih
        ) {
            // Garis Pembatas dengan Efek Shadow
            Divider(
                color = Color.LightGray,
                thickness = 1.dp,
                modifier = Modifier
                    .fillMaxWidth() // Membuat Divider mengisi seluruh lebar layar
                    //.shadow(4.dp, shape = RectangleShape) // Efek bayangan pada garis
            )

            // Row untuk Navigasi
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp), // Margin atas dan bawah untuk navigasi
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly // Membagi ruang sama rata di antara item
            ) {
                // Navigasi Aktivitas
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable { /* Navigate to Aktivitas */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.aktivitas_icon), // Ikon Aktivitas
                        contentDescription = "Aktivitas",
                        tint = Color(0xFF92C947),
                        modifier = Modifier.size(32.dp) // Ukuran ikon
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
                    modifier = Modifier.clickable { /* Navigate to Beranda */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.beranda_icon), // Ikon Beranda
                        contentDescription = "Beranda",
                        tint = Color(0xFF92C947),
                        modifier = Modifier.size(32.dp) // Ukuran ikon
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
                    modifier = Modifier.clickable { /* Navigate to Profil */ }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.profil_icon), // Ikon Profil
                        contentDescription = "Profil",
                        tint = Color(0xFF92C947),
                        modifier = Modifier.size(32.dp) // Ukuran ikon
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
}



@Composable
fun RiwayatScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Riwayat Pemesanan",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 22.dp)
        )

        // Riwayat Pesanan (Dummy Data)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .border(1.dp, Color.Gray)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Nasi Goreng 88",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = "Rp 10.000",
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Selesai",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    color = Color(0xFF92C947)
                )
            }
        }

        // Tambahkan lebih banyak riwayat pemesanan di sini sesuai kebutuhan
    }
}


//@Preview(showBackground = true)
//@Composable
//fun DalamProsesScreenPreview() {
//    DalamProsesScreen()
//}

@Preview(showBackground = true)
@Composable
fun AktivitasScreenPreview() {
    AktivitasScreen() // Menampilkan tampilan "AktivitasScreen" di preview
}
