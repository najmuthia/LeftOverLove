package com.example.leftoverlove.Order

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

//import com.example.leftoverlove.ui.theme.Order.ui.theme.LeftOverLoveTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderSummaryScreen(onBackClick: () -> Unit = {}) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Pesanan", style = MaterialTheme.typography.titleLarge) },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Delivery and Pickup Buttons
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /* Pick up action */ }) {
                    Text("Pick up")
                }
                Button(
                    onClick = { /* Delivery action */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Gray)
                ) {
                    Text("Delivery")
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Address Section
            Text("Alamat", style = MaterialTheme.typography.titleMedium)
            Text("Rumah\nJl. Sumbersari Gg. 4 No. 225M, Kec. Lowokwaru, Kota Malang")

            Spacer(modifier = Modifier.height(8.dp))

            // Order List
            Text("Daftar Pesanan", style = MaterialTheme.typography.titleMedium)
            OrderItem("Nasi Goreng Spesial", 10000)
            OrderItem("Mie Goreng Spesial", 10000)

            Spacer(modifier = Modifier.height(8.dp))

            // Promo Button
            Button(
                onClick = { /* Promo action */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pilih Promo")
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Payment Summary
            Text("Ringkasan Pembayaran", style = MaterialTheme.typography.titleMedium)
            PaymentSummary(subtotal = 20000, shippingFee = 10000, discount = 0, total = 30000)

            Spacer(modifier = Modifier.height(16.dp))

            // Order Button
            Button(
                onClick = { /* Order Now action */ },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Pesan Sekarang")
            }
        }
    }
}

@Composable
fun OrderItem(itemName: String, price: Int) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(itemName)
        Text("Rp $price")
    }
}

@Composable
fun PaymentSummary(subtotal: Int, shippingFee: Int, discount: Int, total: Int) {
    Column {
        Text("Subtotal: Rp $subtotal")
        Text("Biaya pengantaran: Rp $shippingFee")
        Text("Diskon: Rp $discount")
        Text("Total: Rp $total")
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewOrderSummaryScreen() {
    // Wrap the preview in your app theme for proper styling
    MaterialTheme {
        OrderSummaryScreen()
    }
}