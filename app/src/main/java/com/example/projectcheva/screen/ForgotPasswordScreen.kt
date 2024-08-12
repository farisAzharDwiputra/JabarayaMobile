package com.example.projectcheva.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import com.example.projectcheva.FontProvider
import com.example.projectcheva.R

@Composable
@ExperimentalWearMaterialApi
fun ForgotPasswordScreen() {

    val focusManager = LocalFocusManager.current

    var email by remember { mutableStateOf("") }
    val buttonColor = if (email.isEmpty()) "#838080".color else "#2F70B5".color

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
                text = "Lupa Kata Sandi?",
                color = Color.Black,
                fontSize = 32.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = "Masukkan email anda untuk reset password",
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
                )
                .pointerInput(Unit){
                    detectTapGestures(
                        onTap = {
                            // Clear focus on tap
                            focusManager.clearFocus()
                        }
                    )
                },
            verticalArrangement = Arrangement.Center
        ) {
            // Add text above the text field with bold font
            Text(
                text = "Email",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(start = 30.dp, bottom = 10.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp),
                value = email,
                onValueChange = { newEmail ->
                    email = newEmail
                },
                label = {
                    Text(
                        text = "Masukkan Email"
                    )
                },
                isError = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(20.dp)
            )
            Spacer(modifier = Modifier.size(20.dp))
            TextButton(
                onClick = { /* TODO: Implement reset password functionality */ },
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 90.dp)
            ) {
                Text(
                    text = "Kirim Email Reset",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Preview
@Composable
@ExperimentalWearMaterialApi
fun ForgotPasswordScreenPreview() {
        ForgotPasswordScreen()
}
