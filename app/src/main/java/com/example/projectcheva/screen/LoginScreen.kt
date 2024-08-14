package com.example.projectcheva.screen

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(context))
    val authResult by authViewModel.authResult.observeAsState(Pair(false, null))

    // State variables
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }
    var navigateToDashboard by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


    val currentIcon = if (isPasswordVisible) {
        R.drawable.eye_regular
    } else {
        R.drawable.eye_slash_regular
    }

    // Effects
    LaunchedEffect(navigateToDashboard) {
        if (navigateToDashboard) {
            delay(500)
            navController.navigate(Screens.Beranda.route) {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    LaunchedEffect(authResult) {
        authResult.let { (success, message) ->
            if (success) {
                navigateToDashboard = true
            } else {
                val toastMessage = message ?: "Welcome to Jabaraya"
                if (toastMessage.isNotBlank()) {
                    Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
                } else {
                    // Optionally, handle the case where the message is blank
                }
            }
        }
    }

    // Main Layout
    if (isLoading) {
        LoadingScreen()
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = "#68B1FF".color)
        ) {
            HeaderSection()
            LogoSection()
            ContentSection(
                email = email,
                onEmailChange = { email = it },
                password = password,
                onPasswordChange = { password = it },
                isPasswordVisible = isPasswordVisible,
                onPasswordVisibilityChange = { isPasswordVisible = !isPasswordVisible },
                currentIcon = currentIcon,
                onForgotPasswordClick = { navController.navigate(Screens.ForgotPassword.route) },
                onLoginClick = {
                    isLoading = true
                    viewModel.login(email, password) { success, message ->
                        isLoading = false
                        if (success) {
                            viewModel.getUserProfile(SignInMethod.Manual) { profile ->
                                if (profile != null) {
                                    navigateToDashboard = true
                                    saveLoginInMethod(context, SignInMethod.Manual)
                                } else {
                                    errorMessage = "Failed to fetch user profile"
                                }
                            }
                        } else {
                            errorMessage = message
                        }
                    }
                },
                onGoogleSignInClick = {
                    authViewModel.googleSignIn()
                    viewModel.getUserProfile(SignInMethod.Google) { profile ->
                        if (profile != null) {
                            isLoading = true
                            navigateToDashboard = true
                            saveLoginInMethod(context, SignInMethod.Google)
                        } else {
                            errorMessage = "Failed to fetch user profile"
                        }
                        isLoading = false
                    }
                },
                onFacebookSignInClick = {
                    // Facebook sign-in code here
                },
                onRegisterClick = { navController.navigate(Screens.Register.route) }
            )
        }
    }
}

@Composable
fun HeaderSection() {
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
}

@Composable
fun LogoSection() {
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
}

@Composable
fun ContentSection(
    email: String,
    onEmailChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    isPasswordVisible: Boolean,
    onPasswordVisibilityChange: () -> Unit,
    currentIcon: Int,
    onForgotPasswordClick: () -> Unit,
    onLoginClick: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    onFacebookSignInClick: () -> Unit,
    onRegisterClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Color.White,
                shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
            )
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp)
                .padding(horizontal = 15.dp),
            value = email,
            onValueChange = onEmailChange,
            label = { Text(text = "Masukan Email") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            singleLine = true,
            shape = RoundedCornerShape(20.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray
            )
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp)
                .padding(horizontal = 15.dp),
            value = password,
            onValueChange = onPasswordChange,
            label = { Text(text = "Masukan Password") },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            shape = RoundedCornerShape(20.dp),
            trailingIcon = {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(0.075f)
                        .padding(end = 8.dp)
                        .clickable { onPasswordVisibilityChange() },
                    painter = painterResource(id = currentIcon),
                    contentDescription = if (isPasswordVisible) "Hide Password Icon" else "Show Password Icon"
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedLabelColor = Color.Black,
                focusedBorderColor = Color.Black,
                unfocusedBorderColor = Color.Black,
                unfocusedLabelColor = Color.Gray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Gray
            )
        )

        Text(
            modifier = Modifier
                .padding(end = 15.dp)
                .padding(top = 2.dp)
                .padding(bottom = 22.dp)
                .align(Alignment.End)
                .clickable { onForgotPasswordClick() },
            text = "Lupa Kata Sandi?",
            color = Color.Black,
            fontSize = 13.sp,
            fontFamily = FontProvider.urbanist,
            fontWeight = FontWeight.Normal
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 90.dp)
        ) {
            OutlinedButton(
                onClick = onLoginClick,
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2F70B5)),
            ) {
                Text(
                    text = "Masuk",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Text(
                text = "atau",
                modifier = Modifier
                    .clickable { onRegisterClick() }
                    .padding(bottom = 10.dp),
                fontFamily = FontProvider.urbanist,
                fontSize = 16.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }

        SignInOptionsSection(
            onGoogleSignInClick = onGoogleSignInClick,
            onFacebookSignInClick = onFacebookSignInClick
        )

        Text(
            text = buildAnnotatedString {
                append("Tidak punya akun? ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                    append("Buat akun disini")
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable { onRegisterClick() }
                .padding(horizontal = 15.dp),
            fontFamily = FontProvider.urbanist,
            fontSize = 16.sp,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun SignInOptionsSection(
    onGoogleSignInClick: () -> Unit,
    onFacebookSignInClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
    ) {
        OutlinedButton(
            onClick = onGoogleSignInClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 15.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
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

        OutlinedButton(
            onClick = onFacebookSignInClick,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.White),
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
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLoginScreen() {
    val context = LocalContext.current
    val navController = rememberNavController()
    LoginScreen(navController, authViewModel = AuthViewModel(context))
}
