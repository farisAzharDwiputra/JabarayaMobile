package com.example.projectcheva

import android.content.Context
import android.graphics.Color.parseColor
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

@Composable
fun LoginScreen(auth: FirebaseAuth, navController: NavController, googleSignInClient: GoogleSignInClient) {
    val context = LocalContext.current

    // Set default font to Urbanist
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

    var teksEmail by remember {
        mutableStateOf("")
    }

    var teksPassword by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = "#c4e1ff".color)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
                .padding(horizontal = 23.dp)
        ) {
            Text(
                text = "LOGIN",
                color = Color.Black,
                fontSize = 32.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Selamat datang kembali",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "di Jabaraya",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .size(220.dp)
                    .align(Alignment.CenterHorizontally)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .padding(bottom = 22.dp)
                    .padding(horizontal = 15.dp),
                value = teksEmail,
                onValueChange = { teksBaru ->
                    teksEmail = teksBaru
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
                    .padding(bottom = 5.dp)
                    .padding(horizontal = 15.dp),
                value = teksPassword,
                onValueChange = { teksBaru ->
                    teksPassword = teksBaru
                },
                label = {
                    Text(text = "Masukan Password")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(20.dp)
            )
            Text(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .padding(top = 2.dp)
                    .padding(bottom = 22.dp)
                    .align(Alignment.End),
                text = "Lupa Kata Sandi?",
                color = Color.Black,
                fontSize = 13.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            )
            OutlinedButton(
                onClick = {
                    // Trigger the Google sign-in intent
                    val signInIntent = googleSignInClient.signInIntent
                    (context as MainActivity).googleSignInLauncher.launch(signInIntent)
                          },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White // Warna latar belakang tombol
                ),
                border = BorderStroke(2.dp, "#D9D9D9".color)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_google),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = "Masuk dengan Google",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
            OutlinedButton(
                onClick = { /* TODO: Add Facebook Sign-In functionality */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White // Warna latar belakang tombol
                ),
                border = BorderStroke(2.dp, "#D9D9D9".color)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_facebook),
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .padding(end = 10.dp),
                    tint = Color.Unspecified
                )
                Text(
                    text = "Masuk dengan Facebook",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = {
                    if (teksEmail.isEmpty() || teksPassword.isEmpty()) {
                        Toast.makeText(context, "Email and password must not be empty", Toast.LENGTH_SHORT).show()
                    } else {
                        login(auth, teksEmail, teksPassword, context) { message ->
                            if (message == "Login successful!") {
                                // Navigate to the home screen
                                navController.navigate("home")
                            } else {
                                // Show error message
                                println(message)
                            }
                        }
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp)
                    .padding(horizontal = 90.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = "#2F70B5".color // Warna latar belakang tombol
                )
            ) {
                Text(
                    text = "Masuk",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

// Convert hex color string to Color object
val String.color: Color
    get() = Color(parseColor(this))

fun login(auth: FirebaseAuth, email: String, password: String, context: Context, callback: (String) -> Unit) {
    auth.signInWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                callback("Login successful!")
                Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show()
            } else {
                val message = "Login failed: ${task.exception?.message}"
                callback(message)
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    val auth = FirebaseAuth.getInstance()
    val navController = rememberNavController()
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
    val googleSignInClient = GoogleSignIn.getClient(MainActivity(), gso)
    LoginScreen(auth, navController, googleSignInClient)
}