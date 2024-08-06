package com.example.projectcheva.screen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import com.example.projectcheva.FontProvider
import com.example.projectcheva.R
import kotlinx.coroutines.delay

@Composable
@ExperimentalWearMaterialApi
fun OtpScreen() {

    var otp1 by remember { mutableStateOf("") }
    var otp2 by remember { mutableStateOf("") }
    var otp3 by remember { mutableStateOf("") }
    var otp4 by remember { mutableStateOf("") }
    var timer by rememberSaveable { mutableStateOf(60) }
    var isResendEnabled by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = timer) {
        if (timer > 0) {
            delay(1000L)
            timer--
        } else {
            isResendEnabled = true
        }
    }
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
                .background(Color.White,shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "OTP Verification",
                color = "#474646".color,
                fontSize = 32.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier
                    .padding(bottom = 16.dp)
            )
            Text(
                text = "Check to receive the OTP code on",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Email:",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(40.dp))
            Text(
                text = "budaya@gmail.com",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(modifier = Modifier.height(40.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                OtpCircle(otp = otp1, onOtpChange = { otp1 = it })
                OtpCircle(otp = otp2, onOtpChange = { otp2 = it })
                OtpCircle(otp = otp3, onOtpChange = { otp3 = it })
                OtpCircle(otp = otp4, onOtpChange = { otp4 = it })
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Kirim ulang kode dalam: $timer detik",
                color = Gray,
                fontSize = 16.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.Normal
            )

            TextButton(
                onClick = {
                    if (isResendEnabled) {
                        timer = 60
                        isResendEnabled = false
                        // Implement resend code logic here
                    }
                },
                enabled = isResendEnabled
            ) {
                Text(
                    text = "Kirim Ulang Kode",
                    color = if (isResendEnabled) Color.Blue else Gray,
                    fontSize = 16.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = { /* TODO: Implement verify OTP logic */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 90.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = "#2F70B5".color
                )
            ) {
                Text(
                    text = "Verifikasi",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OtpCircle(otp: String, onOtpChange: (String) -> Unit) {
    OutlinedTextField(
        value = otp,
        onValueChange = {
            if (it.length <= 1) onOtpChange(it)
        },
        modifier = Modifier
            .size(60.dp)
            .background(color = "#F6F6F6".color, shape = CircleShape),
        singleLine = true,
        maxLines = 1,
        shape = CircleShape,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = "#2F70B5".color,
            unfocusedBorderColor = "#2F70B5".color,
            focusedTextColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}

@Preview
@Composable
@ExperimentalWearMaterialApi
fun OtpScreenPreview() {
        OtpScreen()
}