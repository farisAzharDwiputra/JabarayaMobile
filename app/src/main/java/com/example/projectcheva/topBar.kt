package com.example.projectcheva

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectcheva.screen.color

@Preview
@Composable
fun topBar() {

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

    Box(
        modifier = Modifier

            .background(
                color = "#c4e1ff".color,
                shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)
            )
            .padding(bottom = 10.dp)
    ) {
        Text(
            text = "SELAMAT DATANG",
            color = Color.Black,
            fontSize = 30.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 90.dp)
                .padding(top = 10.dp)
        )
        Text(
            text = "Tempat kamu mengeksplor bandung " + "\n" +
                    "dengan pengalaman menarik",
            color = Color.Black,
            fontSize = 12.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp)
                .padding(bottom = 80.dp),
        )
        Image(
            painter = painterResource(id = R.drawable.logo_jabaraya),
            contentDescription = "",
            modifier = Modifier
                .size(110.dp)
                .align(Alignment.BottomCenter)
                .padding(top = 30.dp)
        )
    }
}

