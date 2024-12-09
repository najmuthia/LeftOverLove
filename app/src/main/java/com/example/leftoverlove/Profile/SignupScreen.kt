package com.example.leftoverlove.Profile

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest

@Composable
fun SignupScreen(navController: NavController) {
    val auth = FirebaseAuth.getInstance()
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Daftarkan akun Anda!", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(top = 24.dp))
        Spacer(modifier = Modifier.height(16.dp))

        // Nama Field
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Email Field
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Phone Field
        TextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Nomor Telepon") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password Field
        TextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Kata Sandi") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Confirm Password Field
        TextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Konfirmasi Kata Sandi") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))

        // Sign Up Button
        Button(
            onClick = {
                if (password == confirmPassword) {
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                // Update Profile with Name and Phone
                                val user = auth.currentUser
                                val profileUpdates = UserProfileChangeRequest.Builder()
                                    .setDisplayName(name) // Set the display name
                                    .build()

                                user?.updateProfile(profileUpdates)?.addOnCompleteListener { updateTask ->
                                    if (updateTask.isSuccessful) {
                                        // Optionally store phone number elsewhere, not in the profile
                                        // Firestore or Realtime Database can store phone numbers
                                        // For now, let's just navigate to the Profile page
                                        navController.navigate("home")
                                    }
                                }
                            } else {
                                Toast.makeText(navController.context, "Signup gagal", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    Toast.makeText(navController.context, "Password tidak cocok", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(Color(0xFF7B886F))
        ) {
            Text("Daftar", color = Color.White)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(text = "Sudah punya akun?", color = Color.Gray)
            Spacer(modifier = Modifier.width(4.dp))
            TextButton(onClick = { navController.navigate("login") }) {
                Text(text = "Masuk", color = Color(0xFF7B886F))
            }
        }
    }
}
