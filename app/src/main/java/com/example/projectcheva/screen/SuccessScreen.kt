 package com.example.projectcheva.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectcheva.R

 @Composable
fun SuccessScreen(onContinue: () -> Unit) {
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

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = "#c4e1ff".color),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
                .background(color = Color.Cyan),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),//belum ada logo,
                contentDescription = "Checkmark",
                modifier = Modifier.size(80.dp)
            )
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            text = "Password Berhasil Diubah",
            color = Color.Black,
            fontSize = 24.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = onContinue,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 60.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Blue
            ),
            shape = RoundedCornerShape(20.dp)
        ) {
            Text(
                text = "Lanjutkan",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Preview
@Composable
fun SuccessScreenPreview() {
    Surface {
        SuccessScreen(onContinue = {})
    }
}
