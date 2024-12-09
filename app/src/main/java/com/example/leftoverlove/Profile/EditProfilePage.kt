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
import androidx.compose.material3.AlertDialog
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
import com.google.firebase.firestore.FirebaseFirestore

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfilePage(navController: NavController? = null) {
    val auth = FirebaseAuth.getInstance()
    val user = auth.currentUser

    var name by remember { mutableStateOf(user?.displayName ?: "") }
    var phone by remember { mutableStateOf(user?.phoneNumber ?: "") }
    var email by remember { mutableStateOf(user?.email ?: "") }
    var address by remember { mutableStateOf("Jalan Sumbersari Gg. 4 No. 225M, Kec. Lowokwaru, Kota Malang") }

    var showConfirmationDialog by remember { mutableStateOf(false) }
    var saveStatus by remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Edit Profil", fontSize = 20.sp, fontWeight = FontWeight.Bold)
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
            // Header Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFF9F9F9))
                    .padding(vertical = 24.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Image(
                        painter = painterResource(id = R.drawable.login1),
                        contentDescription = "Foto Profil",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(120.dp)
                            .clip(CircleShape)
                            .border(2.dp, Color(0xFF7B886F), CircleShape)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Ganti Foto",
                        color = Color.White,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.clickable { /* Tambahkan logika ganti foto */ }
                    )
                }
            }

            // Input Fields Section
            Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                Text("Nama", style = TextStyle(fontWeight = FontWeight.Bold))
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth().background(Color.White, shape = MaterialTheme.shapes.small)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text("Nomor Telepon", style = TextStyle(fontWeight = FontWeight.Bold))
                TextField(
                    value = phone,
                    onValueChange = { phone = it },
                    modifier = Modifier.fillMaxWidth().background(Color.White, shape = MaterialTheme.shapes.small)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text("Email", style = TextStyle(fontWeight = FontWeight.Bold))
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth().background(Color.White, shape = MaterialTheme.shapes.small)
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text("Alamat", style = TextStyle(fontWeight = FontWeight.Bold))
                TextField(
                    value = address,
                    onValueChange = { address = it },
                    modifier = Modifier.fillMaxWidth().background(Color.White, shape = MaterialTheme.shapes.small)
                )
                Spacer(modifier = Modifier.height(24.dp))

                // Save Button
                Button(
                    onClick = { showConfirmationDialog = true },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B886F))
                ) {
                    Text("Simpan Perubahan", color = Color.White)
                }

                // Confirmation Dialog
                if (showConfirmationDialog) {
                    AlertDialog(
                        onDismissRequest = { showConfirmationDialog = false },
                        title = { Text("Konfirmasi") },
                        text = { Text("Apakah Anda yakin ingin menyimpan perubahan?") },
                        confirmButton = {
                            TextButton(onClick = {
                                showConfirmationDialog = false
                                // Memperbarui profil Firebase Authentication (nama)
                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(name)
                                    .build()

                                user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                                    if (updateTask.isSuccessful) {
                                        // Menyimpan data nomor telepon dan alamat di Firestore
                                        val db = FirebaseFirestore.getInstance()
                                        val userRef = db.collection("users").document(user.uid)

                                        val userData = hashMapOf(
                                            "phone" to phone,
                                            "address" to address
                                        )
                                        userRef.set(userData).addOnSuccessListener {
                                            saveStatus = "Berhasil menyimpan perubahan!"
                                        }.addOnFailureListener {
                                            saveStatus = "Gagal menyimpan perubahan di Firestore."
                                        }
                                    } else {
                                        saveStatus = "Gagal memperbarui profil."
                                    }
                                }
                            }) {
                                Text("Ya")
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showConfirmationDialog = false }) {
                                Text("Tidak")
                            }
                        }
                    )
                }

                // Status Pesan
                saveStatus?.let { status ->
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = status,
                        color = if (status.startsWith("Berhasil")) Color.Green else Color.Red,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}
