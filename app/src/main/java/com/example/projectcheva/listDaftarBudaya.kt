package com.example.projectcheva

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun listDaftarIsiBudaya(){
    LazyRow(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        items(3) { index->
            listDaftarBudaya()
        }
    }
}

@Composable
fun listDaftarBudaya(){

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

    Row( modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Spacer(modifier = Modifier.height(28.dp))
        Column(modifier = Modifier
            .padding(horizontal = 24.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            Card(
                modifier = Modifier
                    .height(245.dp)
                    .border(
                        width = 1.dp,                  // Width of the border
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp),           // Color of the border
                    )
                    .shadow(
                        elevation = 6.dp ,
                        shape = RoundedCornerShape(8.dp)
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .size(250.dp)
                        .background(color = Color.White),
                ) {
                    Text(
                        text= "Tari di Bandung",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(start = 20.dp)
                            .padding(top = 10.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.Center),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.tari_bandung),
                            contentDescription = "",
                            modifier = Modifier
                                .size(210.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(top = 27.dp)

                         )
                     }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedButton(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxWidth()
                                .size(40.dp)
                                .padding(horizontal = 18.dp)
                                .align(Alignment.CenterHorizontally)
                                .padding(bottom = 10.dp),
                            shape = RoundedCornerShape(8.dp),
                            border = BorderStroke(1.dp, Color.LightGray)
                        ) {
                            Text(
                                text= "Lihat lebih banyak",
                                color = Color.Black,
                                fontSize = 11.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.Medium,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun listDaftarBudayaPreview(){
    listDaftarIsiBudaya()
}
