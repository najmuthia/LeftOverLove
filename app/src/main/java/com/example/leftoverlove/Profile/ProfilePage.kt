package com.example.leftoverlove.Profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun ProfilePage(navController: NavController? = null) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFB4DC7F))
                .padding(vertical = 24.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painter = painterResource(id = R.drawable.login1), // Ganti dengan gambar profil Anda
                    contentDescription = "Foto Profil",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(120.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0xFF7B886F), CircleShape)
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Nama Profil dengan Ikon
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(user?.displayName ?: "Nama Tidak Tersedia", color = Color.White, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Nomor HP dengan Ikon
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.phone), contentDescription = "Icon Phone", modifier = Modifier.size(24.dp), tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(user?.phoneNumber ?: "Nomor Tidak Tersedia", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Email dengan Ikon
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.mail), contentDescription = "Icon Email", modifier = Modifier.size(24.dp), tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(user?.email ?: "Email Tidak Tersedia", color = Color.White)
                }

                Spacer(modifier = Modifier.height(8.dp))

                // Poin dengan Ikon
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(painter = painterResource(id = R.drawable.premium_product_1), contentDescription = "Icon Star", modifier = Modifier.size(24.dp), tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("25 poin", color = Color.White, style = MaterialTheme.typography.bodySmall)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Menu Options with Icons from Drawable
        MenuOption(
            text = "Edit Profil",
            icon = painterResource(id = R.drawable.icon_edit),
            onClick = { navController?.navigate("editProfile") }
        )
        MenuOption(
            text = "Aktivitasku",
            icon = painterResource(id = R.drawable.icon_aktivitas),
            onClick = { navController?.navigate("aktivitasku") }
        )
        MenuOption(
            text = "Donasiku",
            icon = painterResource(id = R.drawable.icon_donasi),
            onClick = { navController?.navigate("donasiku") }
        )
        MenuOption(
            text = "Favoritku",
            icon = painterResource(id = R.drawable.icon_favorit),
            onClick = { navController?.navigate("favoritku") }
        )
        MenuOption(
            text = "Keluar",
            icon = painterResource(id = R.drawable.log),
            onClick = {
                Firebase.auth.signOut()
                navController?.navigate("login")
            },
            textColor = Color.Red
        )
    }
}


@Composable
fun MenuOption(
    text: String,
    onClick: () -> Unit,
    icon: Painter,
    textColor: Color = Color.Black
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(vertical = 12.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = textColor, style = MaterialTheme.typography.bodyMedium)
    }
}
