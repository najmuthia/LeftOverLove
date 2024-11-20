package com.example.papbroom


import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.papbroom.ui.theme.PAPBRoomTheme

//import com.example.leftoverlove.R

@Composable
fun DonasiScreen(onPaymentOptionClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Donasi Sekarang",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 22.dp)
        )

        // List of donation options
        val donationAmounts = listOf(5000, 10000, 20000, 50000, 100000, 200000)
        var selectedAmount by remember { mutableStateOf<Int?>(null) }

        donationAmounts.forEach { amount ->
            OutlinedButton(
                onClick = { selectedAmount = amount },
                border = if (selectedAmount == amount) BorderStroke(2.dp, Color(0xFF92C947)) else BorderStroke(1.dp, Color.Gray),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
            ) {
                Text("Rp${amount}")
            }
            Spacer(modifier = Modifier.height(10.dp)) // Tambahkan jarak antar item
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Custom donation amount
        var customAmount by remember { mutableStateOf("") }
        OutlinedTextField(
            value = customAmount,
            onValueChange = { customAmount = it },
            label = { Text("Rp") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(70.dp))

        // Button to go to payment options
        Button(
            onClick = onPaymentOptionClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF92C947))
        ) {
            Text("Pilih Opsi Pembayaran")
        }
    }
}

@Composable
fun PaymentMethodScreen(onProceedClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Metode Pembayaran",
            fontSize = 27.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Payment methods categories with associated images
        val paymentMethods = mapOf(
            "E-Wallet" to listOf(
                Pair("Gopay", R.drawable.gopay_logo),
                Pair("Shopeepay", R.drawable.spay_logo),
                Pair("OVO", R.drawable.ovo_logo)
            ),
            "Bank Transfer" to listOf(
                Pair("Mandiri", R.drawable.mandiri_logo),
                Pair("BSI", R.drawable.bsi_logo),
                Pair("BRI", R.drawable.bri_logo)
            ),
            "Gerai Offline" to listOf(
                Pair("Indomaret", R.drawable.indomaret_logo),
                Pair("Alfamart", R.drawable.alfamart_logo),
                Pair("Alfamidi", R.drawable.alfamidi_logo)
            )
        )
        var selectedMethod by remember { mutableStateOf<Pair<String, String>?>(null) }

        paymentMethods.forEach { (category, methods) ->
            Text(
                text = category,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            methods.forEach { (method, imageRes) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { selectedMethod = category to method }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = selectedMethod == category to method,
                        onClick = { selectedMethod = category to method },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color(0xFF92C947),
                            unselectedColor = Color.Gray
                        )
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "$method logo",
                        modifier = Modifier.size(40.dp) // Adjust the size as needed
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = method)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Button to proceed with payment
        Button(
            onClick = onProceedClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF92C947))
        ) {
            Text("Lanjutkan Pembayaran")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DonasiScreenPreview() {
    DonasiScreen(onPaymentOptionClick = {})
}

@Preview(showBackground = true)
@Composable
fun PaymentMethodScreenPreview() {
    PaymentMethodScreen(onProceedClick = {})
}
