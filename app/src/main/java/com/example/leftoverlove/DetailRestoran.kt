package com.example.leftoverlove

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.leftoverlove.ui.theme.LeftOverLoveTheme
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun DetailRestoran(onBack: () -> Unit) {
    val selectedItems = remember { mutableStateListOf<MenuItem>() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9)) // Background Gray
    ) {
        // Gunakan LazyColumn untuk menggulirkan seluruh tampilan secara vertikal
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 20.dp) // Memberikan ruang untuk Bottom Bar
        ) {
            // Header
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(vertical = 8.dp, horizontal = 16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(onClick = onBack) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Detail Restoran",
                            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                            textAlign = TextAlign.Center,
                            modifier = Modifier.weight(6f)
                        )
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }

            // Gambar Restoran
            item {
                Image(
                    painter = painterResource(id = R.drawable.nasi_goreng_88),
                    contentDescription = "Restaurant Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(280.dp),
                    contentScale = ContentScale.Crop
                )
            }

            // Info Restoran
            item {
                Card(
                    modifier = Modifier
                        .offset(y = (-32).dp)
                        .fillMaxWidth()

                        .padding(vertical = 16.dp) // Membuat ruang lebih besar di atas dan bawah
                        .shadow(8.dp, shape = RoundedCornerShape(16.dp)),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFF9F9F9))
                ) {
                    Column(
                        modifier = Modifier
                            .height(150.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .padding(top=25.dp, start = 16.dp )  // Menambahkan padding di dalam Row
                        ) {

                            Spacer(modifier = Modifier.width(8.dp))
                            Column {
                                Text(
                                    text = "Nasi Goreng 88",
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Nasi Goreng, Mie Goreng",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "Jalan Soekarno Hatta Nomor 97",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Gray
                                )
                            }

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Spacer(modifier = Modifier.weight(4f))
                                Text(
                                    text = "4.8",
                                    style = MaterialTheme.typography.titleLarge,
                                    fontWeight = FontWeight.Bold
                                )

                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = "Rating",
                                    tint = Color(0xFFFFD700)
                                )
                                Spacer(modifier = Modifier.weight(1f))
                            }
                        }
                    }
                }
            }

            // Menu Items
            items(menuList) { menuItem ->
                MenuItemCard(menuItem = menuItem, onAddToCart = { addedItem ->
                    val existingItem = selectedItems.find { it.name == addedItem.name }
                    if (existingItem == null) {
                        selectedItems.add(addedItem.copy(stock = 1))
                    } else {
                        val index = selectedItems.indexOf(existingItem)
                        selectedItems[index] =
                            existingItem.copy(stock = existingItem.stock + 1)
                    }
                })
            }
        }

        // Bottom Bar
        if (selectedItems.isNotEmpty()) {
            val navController = rememberNavController()
            BottomBar(
                modifier = Modifier.align(Alignment.BottomCenter),
                selectedItems = selectedItems,
                navController = navController
            )
        }
    }
}


@Composable
fun MenuItemCard(menuItem: MenuItem, onAddToCart: (MenuItem) -> Unit) {
    var isAddingToCart by remember { mutableStateOf(false) }
    var quantity by remember { mutableIntStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color.White, shape = RoundedCornerShape(8.dp))
            //.border(1.dp, Color.LightGray, RoundedCornerShape(8.dp))
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = menuItem.name,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Black
                    ),
                    modifier = Modifier.padding(bottom = 4.dp)
                )
                Text(
                    text = "Rp ${menuItem.price}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )
                Text(
                    text = "Tersisa ${menuItem.stock} stok",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.Red,
                    modifier = Modifier.padding(bottom = 8.dp) // Jarak untuk tombol tambah
                )

                // Menampilkan tombol - dan + jika item sudah ditambahkan
                if (isAddingToCart) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        IconButton(
                            onClick = {
                                if (quantity > 1) {
                                    quantity -= 1
                                } else {
                                    // Jika quantity 1, reset ke tombol tambah lagi
                                    quantity = 0
                                    isAddingToCart = false
                                }
                            },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.iconmin),
                                contentDescription = "Kurangi",
                                tint = Color.Unspecified
                            )
                        }

                        Text(
                            text = quantity.toString(),
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )

                        IconButton(
                            onClick = {
                                if (quantity < menuItem.stock) quantity += 1
                            },
                            modifier = Modifier.size(36.dp)
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.iconplus),
                                contentDescription = "Tambah",
                                tint = Color.Unspecified
                            )
                        }
                    }
                } else {
                    // Jika belum ditambahkan ke keranjang, tampilkan tombol tambah
                    IconButton(
                        onClick = {
                            isAddingToCart = true
                            quantity = 1 // Menambah 1 item pertama kali
                            onAddToCart(menuItem)
                        },
                        modifier = Modifier.size(65.dp)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.tambahicon),
                            contentDescription = "Tambah Icon",
                            tint = Color.Unspecified
                        )
                    }
                }
            }

            // Gambar di sebelah kanan
            Image(
                painter = painterResource(id = menuItem.imageRes),
                contentDescription = menuItem.name,
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(8.dp))
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Garis abu-abu tipis
        HorizontalDivider(color = Color.LightGray, thickness = 1.dp)
    }
}

@Composable
fun BottomBar( modifier: Modifier = Modifier,
               selectedItems: List<MenuItem>,
               navController: NavController
) {
    val totalItems = selectedItems.sumOf { it.stock }
    val totalPrice = selectedItems.sumOf { it.price * it.stock }


    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp)
            .background(
                Color(0xFF7B886F),
                shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
            )
            .padding(horizontal = 16.dp)
            .clickable {
                // Menavigasi ke screen orderSummary saat Box ditekan
                navController.navigate("OrderSummary")
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "$totalItems item",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color(0xFFF9F9F9)
                )
                Text(
                    text = selectedItems.joinToString { it.name },
                    style = MaterialTheme.typography.bodySmall,
                    color = Color(0xFFF9F9F9)
                )
            }
            Text(
                text = "Rp $totalPrice",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White
            )
        }
    }
}

// Data Class for Menu Items
data class MenuItem(
    val name: String,
    val price: Int,
    val stock: Int,
    val imageRes: Int
)

// Sample Menu Data
val menuList = listOf(
    MenuItem("Nasi Goreng Spesial", 10000, 5, R.drawable.nasi_goreng_88),
    MenuItem("Mie Goreng Spesial", 10000, 10, R.drawable.nasi_goreng_88),
    MenuItem("Es Teh", 5000, 20, R.drawable.nasi_goreng_88)
)


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LeftOverLoveTheme {
        val navController = rememberNavController()

        DetailRestoran(onBack = {})
    }
}