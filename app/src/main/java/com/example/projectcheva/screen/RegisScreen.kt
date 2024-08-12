package com.example.projectcheva.screen

import android.graphics.Color.parseColor
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.wear.compose.material.OutlinedButton
import com.example.projectcheva.FontProvider
import com.example.projectcheva.R
import com.example.projectcheva.Screens
import com.example.projectcheva.retrofit.AuthViewModel
import com.example.projectcheva.retrofit.AuthViewModelFactory

@Composable
fun RegisScreen(navController: NavController) {

    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(context))
    // Set default font to Urbanist
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var teksRegisEmail by remember { mutableStateOf("") }
    var teksRegisNomer by remember { mutableStateOf("") }
    var teksCretedPassword by remember { mutableStateOf("") }
    var teksConfirmPassword by remember { mutableStateOf("") }

    val isDataValid = remember(teksRegisEmail, teksRegisNomer, teksCretedPassword, teksConfirmPassword) {
        teksRegisEmail.isNotEmpty() && teksRegisNomer.isNotEmpty() && teksCretedPassword.isNotEmpty() && teksConfirmPassword.isNotEmpty()
    }

    val countryCodes = listOf("+62", "+1", "+91")
    var selectedCountryCode by remember { mutableStateOf("+62") }
    val borderColor = "#838080".color

    fun formatPhoneNumber(countryCode: String, phoneNumber: String): String {
        return if (countryCode == "+62") {
            "0" + phoneNumber.substringAfter(countryCode)
        } else {
            countryCode + phoneNumber
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = "#68B1FF".color)
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
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "REGISTER",
                    color = Color.Black,
                    fontSize = 32.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = "Jelajahi Jabaraya!",
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontFamily = FontProvider.urbanist,
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
                )
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp)
                    .padding(bottom = 20.dp)
                    .size(70.dp)
                    .padding(horizontal = 20.dp),
                value = teksRegisEmail,
                onValueChange = { inputEmail -> teksRegisEmail = inputEmail },
                label = { Text(text = "Masukan Email") },
                isError = false,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .size(70.dp)
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                CustomOutlinedBox(
                    selectedCountryCode = selectedCountryCode,
                    onCountryCodeSelected = { selectedCountryCode = it },
                    countryCodes = countryCodes,
                    borderColor = borderColor
                )
                Spacer(modifier = Modifier.width(8.dp))
                OutlinedTextField(
                    modifier = Modifier.weight(1f),
                    value = teksRegisNomer,
                    onValueChange = { inputNomer -> teksRegisNomer = inputNomer },
                    label = { Text(text = "Masukan Nomer Telepon") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                    singleLine = true,
                    maxLines = 1,
                    shape = RoundedCornerShape(20.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor
                    )
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .size(70.dp)
                    .padding(horizontal = 20.dp),
                value = teksCretedPassword,
                onValueChange = { createdPassword -> teksCretedPassword = createdPassword },
                label = { Text(text = "Buat Kata Sandi") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                singleLine = true,
                maxLines = 1,
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
                    .size(70.dp)
                    .padding(horizontal = 20.dp),
                value = teksConfirmPassword,
                onValueChange = { confirmPassword -> teksConfirmPassword = confirmPassword },
                label = { Text(text = "Konfirmasi Kata Sandi") },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(20.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor
                )
            )
            //Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Sudah punya akun? ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Black)) {
                            append("Masuk disini")
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .clickable {
                           navController.navigate(Screens.Login.route)
                        },
                    fontFamily = FontProvider.urbanist,
                    fontSize = 16.sp,
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            OutlinedButton(
                onClick = {
                    viewModel.register(name, email, phone, password, passwordConfirmation, selectedCountryCode) { success, message ->
                        if (success) {
                            // Debug log
                            Log.d("RegisterScreen", "Registration successful. Switching to LoginScreen.")
                            navController.navigate("login") {
                                popUpTo("register") { inclusive = true }
                            }
                        } else {
                            errorMessage = message
                            // Debug log
                            Log.d("RegisterScreen", "Registration failed: $message")
                        }
                    }
                },
                enabled = isDataValid,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 40.dp)
                    .size(60.dp)
                    .padding(horizontal = 90.dp),
                shape = RoundedCornerShape(30.dp),
                colors = androidx.wear.compose.material.ButtonDefaults.buttonColors(
                    backgroundColor = if (isDataValid) "#2F70B5".color else "#838080".color // Conditional color change
                ),
            ) {
                Text(
                    text = "Daftar",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun CustomOutlinedBox(
    selectedCountryCode: String,
    onCountryCodeSelected: (String) -> Unit,
    countryCodes: List<String>,
    borderColor: Color
) {
    var expanded by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .border(
                width = 1.dp,
                color = borderColor,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { expanded = true }
            .background(Color.White, RoundedCornerShape(20.dp))
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = selectedCountryCode,
                color = "#5C5C5C".color,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.ExtraBold)
            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Dropdown Arrow")
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            countryCodes.forEach { code ->
                DropdownMenuItem(
                    text = { Text(text = code) },
                    onClick = {
                        onCountryCodeSelected(code)
                        expanded = false
                    }
                )
            }
        }
    }
}

// Hex color extension
val String.color: Color
    get() = Color(parseColor(this))

@Preview
@Composable
fun RegisScreenPreview() {
    Surface {
        val context = LocalContext.current
        RegisScreen(navController = NavController(context))
    }
}