package com.example.projectcheva.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projectcheva.FontProvider
import com.example.projectcheva.R
import com.example.projectcheva.Screens
import com.example.projectcheva.SignInMethod
import com.example.projectcheva.retrofit.AuthTokenManager.saveLoginInMethod
import com.example.projectcheva.retrofit.AuthViewModel
import com.example.projectcheva.retrofit.AuthViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(context))
    val authResult by authViewModel.authResult.collectAsState()

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var navigateToDashboard by remember { mutableStateOf(false) }
    var shouldSignIn by remember { mutableStateOf(false) }
    val loginResult by remember { mutableStateOf<Pair<Boolean, String>?>(null) }


    // Show the loading screen until the dashboard is navigated to
    LaunchedEffect(navigateToDashboard) {
        if (navigateToDashboard) {
            // Delay to simulate loading
            delay(500) // Adjust delay as needed
            navController.navigate(Screens.Beranda.route) {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    // Use LaunchedEffect to react to authResult changes
    LaunchedEffect(authResult) {
        authResult?.let { (success, message) ->
            if (success) {
                // Navigate to the dashboard on successful sign-in
                navController.navigate(Screens.Beranda.route) {
                    popUpTo("login") { inclusive = true }
                }
            } else {
                // Show error message on failed sign-in
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    // Trigger sign-in when shouldSignIn changes to true
    LaunchedEffect(shouldSignIn) {
        if (shouldSignIn) {
            viewModel.googleSignIn()
            shouldSignIn = false
        }
    }

    // Observe login result and show messages
    loginResult?.let { (success, message) ->
        LaunchedEffect(loginResult) {
            if (success) {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
                //navigateToDashboard = true
            } else {
                Toast.makeText(context, message, Toast.LENGTH_LONG).show()
            }
        }
    }

    if (isLoading || navigateToDashboard) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = "#68B1FF".color)
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
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Selamat datang kembali",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "di Jabaraya",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = FontProvider.urbanist,
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
                        .align(Alignment.End)
                        .padding(start = 24.dp)
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
                        .padding(top = 8.dp)
                        .padding(horizontal = 15.dp),
                    value = email,
                    onValueChange = { email = it },
                    label = { Text(text = "Masukan Email") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Gray,
                    )
                )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 5.dp)
                        .padding(horizontal = 15.dp),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text(text = "Masukan Password") },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        focusedLabelColor = Color.Black,
                        unfocusedLabelColor = Color.Gray,
                    )
                )
                Text(
                    modifier = Modifier
                        .padding(end = 15.dp)
                        .padding(top = 2.dp)
                        .padding(bottom = 22.dp)
                        .align(Alignment.End)
                        .clickable {
                            navController.navigate(Screens.ForgotPassword.route)
                        },
                    text = "Lupa Kata Sandi?",
                    color = Color.Black,
                    fontSize = 13.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.Normal
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 90.dp)
                    ) {
                        OutlinedButton(
                            onClick = {
                                // Show loading screen
                                isLoading = true
                                Log.d("LoginScreen", "Attempting login with email: $email")
                                viewModel.login(email, password) { success, message ->
                                    Log.d("LoginScreen", "Login callback received - Success: $success, Message: $message")
                                    isLoading = false
                                    if (success) {
                                        Log.d("LoginScreen", "Fetching user profile after successful login")
                                        // Use the appropriate SignInMethod based on your scenario
                                        val signInMethod = SignInMethod.Manual
                                        viewModel.getUserProfile(signInMethod) { profile ->
                                            if (profile != null) {
                                                Log.d("LoginScreen", "User profile fetched: $profile")
                                                // Set flag to start navigation
                                                navigateToDashboard = true
                                                // Save sign-in method
                                                saveLoginInMethod(context, signInMethod)
                                            } else {
                                                Log.d("LoginScreen", "Failed to fetch user profile")
                                                errorMessage = "Failed to fetch user profile"
                                            }
                                        }
                                    } else {
                                        Log.d("LoginScreen", "Login failed with message: $message")
                                        errorMessage = message
                                    }
                                }
                            },
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFF2F70B5)
                            ),
                        ) {
                            Text(
                                text = "Masuk",
                                color = Color.White,
                                fontSize = 24.sp,
                                fontFamily = FontProvider.urbanist,
                                fontWeight = FontWeight.SemiBold,
                            )
                        }
                        Text(
                            text = "atau",
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screens.Login.route)
                                }
                                .padding(bottom = 10.dp),
                            fontFamily = FontProvider.urbanist,
                            fontSize = 16.sp,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }

                OutlinedButton(
                    {
                        Log.d("LoginScreen", "Attempting Google Sign-In")
                        authViewModel.viewModelScope.launch {
                            try {
                                // Trigger Google Sign-In
                                authViewModel.googleSignIn()
                                // Handle sign-in result
                                Log.d("LoginScreen", "Google Sign-In successful")
                                // Use the appropriate SignInMethod based on your scenario
                                val signInMethod = SignInMethod.Google
                                // Fetch user profile
                                viewModel.getUserProfile(signInMethod) { profile ->
                                    if (profile != null) {
                                        Log.d("LoginScreen", "User profile fetched: $profile")
                                        // Show loading screen
                                        isLoading = true
                                        // Set flag to start navigation
                                        navigateToDashboard = true
                                        // Save sign-in method
                                        saveLoginInMethod(context, signInMethod)
                                    } else {
                                        Log.d("LoginScreen", "Failed to fetch user profile")
                                        errorMessage = "Failed to fetch user profile"
                                    }
                                    // Hide loading screen
                                    isLoading = false
                                }
                            } catch (e: Exception) {
                                Log.e("LoginScreen", "Exception during Google Sign-In: ${e.message}")
                                errorMessage = "An error occurred during Google Sign-In"
                                isLoading = false
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 15.dp)
                        .padding(horizontal = 15.dp),
                    shape = RoundedCornerShape(20.dp),
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    ),
                    border = BorderStroke(2.dp, Color(0xFFD9D9D9))
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
                        text = "Masuk dengan google",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = FontProvider.urbanist,
                        fontWeight = FontWeight.Normal
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp)
                ) {
                    OutlinedButton(
                        onClick = {
                            // Insert Facebook sign-in code here
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp), // Increased spacing below the button
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.outlinedButtonColors(
                            contentColor = Color.White
                        ),
                        border = BorderStroke(2.dp, Color(0xFFD9D9D9))
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
                            text = "Masuk dengan facebook",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.Normal
                        )
                    }

                    Spacer(modifier = Modifier.height(10.dp)) // Adjust spacing as needed

                    Text(
                        text = buildAnnotatedString {
                            append("Tidak punya akun? ")
                            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                                append("Buat akun disini")
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable {
                                navController.navigate(Screens.Register.route)
                            },
                        fontFamily = FontProvider.urbanist,
                        fontSize = 16.sp,
                        color = Color.Black,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val context = LocalContext.current
    val navController = rememberNavController()
    LoginScreen(navController, authViewModel = AuthViewModel(context))
}
