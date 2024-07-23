package com.example.projectcheva.screen


import android.graphics.Color.parseColor
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
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
import com.example.projectcheva.R
import kotlinx.coroutines.delay

@Composable
@ExperimentalWearMaterialApi
fun OtpScreen() {
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
            .background(color = "#c4e1ff".colorRegis)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Masukkan Kode OTP",
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            text = "Kode telah dikirim ke email Anda",
            color = Color.Black,
            fontSize = 20.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Medium
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
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal
        )

        TextButton(
            onClick = {
                if(isResendEnabled) {
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
                fontFamily = fontFamily,
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
                containerColor = "#2F70B5".colorRegis
            )
        ) {
            Text(
                text = "Verifikasi",
                color = Color.White,
                fontSize = 24.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold
            )
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
            .background(Color.White, shape = CircleShape),
        singleLine = true,
        maxLines = 1,
        shape = CircleShape,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        visualTransformation = VisualTransformation.None,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Transparent,
            unfocusedBorderColor = Color.Transparent,
            focusedTextColor = Color.Black,
            cursorColor = Color.Black
        )
    )
}

val String.Companion
    get() = Color(parseColor(this))

@Preview
@Composable
@ExperimentalWearMaterialApi
fun OtpScreenPreview() {
    Surface {
        OtpScreen()
    }
}