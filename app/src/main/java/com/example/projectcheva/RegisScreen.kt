package com.example.projectcheva

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.wear.compose.material.OutlinedButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

@Composable
fun RegisScreen(auth: FirebaseAuth, firestore: FirebaseFirestore, navController: NavController) {
    val context = LocalContext.current
    remember { SnackbarHostState() }
    //set default font menjadi urbanist
    val fontFamily = FontFamily(
        Font(R.font.urbanist_black, FontWeight.Black),
        Font(R.font.urbanist_bold, FontWeight.Bold),
        Font(R.font.urbanist_extrabold, FontWeight.ExtraBold),
        Font(R.font.urbanist_extralight, FontWeight.ExtraLight),
        Font(R.font.urbanist_light, FontWeight.Light),
        Font(R.font.urbanist_medium, FontWeight.Medium),
        Font(R.font.urbanist_regular, FontWeight.Normal),
        Font(R.font.urbanist_semibold, FontWeight.SemiBold),
        Font(R.font.urbanist_thin, FontWeight.Thin),
    )

    var teksRegisEmail by remember {
        mutableStateOf("")
    }
    var teksRegisNomer by remember {
        mutableStateOf("")
    }
    var teksCretedPassword by remember {
        mutableStateOf("")
    }
    var teksConfirmPassword by remember {
        mutableStateOf("")
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = "#c4e1ff".colorRegis)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp, start = 33.dp, end = 23.dp)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(vertical = 80.dp)
                    .padding(end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                Text(
                    text = "REGISTER",
                    color = Color.Black,
                    fontSize = 32.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Jelajahi Jabaraya!",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium
                )
            }
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .size(220.dp)
                    .padding(start = 30.dp)
                    .align(Alignment.TopEnd)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                ),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(bottom = 20.dp)
                    .size(70.dp)
                    .padding(horizontal = 20.dp),
                value = teksRegisEmail,
                onValueChange = { inputEmail ->
                    teksRegisEmail = inputEmail
                },
                label = {
                    Text(text = "Masukan Email")
                },
                isError = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(20.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .size(70.dp)
                    .padding(horizontal = 20.dp),
                value = teksRegisNomer,
                onValueChange = { inputNomer ->
                    teksRegisNomer = inputNomer
                },
                label = {
                    Text(text = "Masukan Nomer Telepon")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(20.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .size(70.dp)
                    .padding(horizontal = 20.dp),
                value = teksCretedPassword,
                onValueChange = { createdPassword ->
                    teksCretedPassword = createdPassword
                },
                label = {
                    Text(text = "Buat Kata Sandi")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(20.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .size(70.dp)
                    .padding(horizontal = 20.dp),
                value = teksConfirmPassword,
                onValueChange = { confirmPassword ->
                    teksConfirmPassword = confirmPassword
                },
                label = {
                    Text(text = "Buat Kata Sandi")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(20.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = {
                    if (validateRegistrationInputs(
                            teksRegisEmail,
                            teksRegisNomer,
                            teksCretedPassword,
                            teksConfirmPassword
                        )
                    ) {
                        register(auth, firestore, teksRegisEmail, teksCretedPassword, teksRegisNomer) { message ->
                            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                            if (message.contains("successful")) {
                                navController.navigate("login")
                            }
                        }
                    } else {
                        Toast.makeText(
                            context,
                            "Invalid inputs. Please check your information.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
                    .size(60.dp)
                    .padding(horizontal = 90.dp),
                shape = RoundedCornerShape(30.dp),
                colors = androidx.wear.compose.material.ButtonDefaults.buttonColors(
                    backgroundColor = "#2F70B5".color// Warna latar belakang tombol
                ),
            ) {
                Text(
                    text = "Daftar",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

fun validateRegistrationInputs(
    email: String,
    phone: String,
    password: String,
    confirmPassword: String,
): Boolean {
    return email.isNotEmpty() &&
            phone.isNotEmpty() &&
            password.isNotEmpty() &&
            confirmPassword.isNotEmpty() &&
            password == confirmPassword
}

//hex warna menjadi string
val String.colorRegis
    get()= Color(android.graphics.Color.parseColor(this))

fun register(
    auth: FirebaseAuth,
    firestore: FirebaseFirestore,
    email: String,
    password: String,
    phone: String,
    callback: (String) -> Unit
) {
    auth.createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = auth.currentUser
                user?.let {
                    addUserToFirestore(firestore, it.uid, email, phone) { message ->
                        callback(message)
                    }
                } ?: callback("Registration failed: User is null")
            } else {
                callback("Registration failed: ${task.exception?.message}")
            }
        }
}

fun addUserToFirestore(
    firestore: FirebaseFirestore,
    uid: String,
    email: String,
    phone: String,
    callback: (String) -> Unit
) {
    val user = hashMapOf(
        "uid" to uid,
        "email" to email,
        "phone" to phone
    )
    firestore.collection("users")
        .document(uid)
        .set(user)
        .addOnSuccessListener {
            callback("Registration successful!")
        }
        .addOnFailureListener { e ->
            callback("User registration successful but failed to add user to Firestore: ${e.message}")
        }
    }