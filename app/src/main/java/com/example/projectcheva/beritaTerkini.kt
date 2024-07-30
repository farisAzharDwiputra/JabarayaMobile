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
import androidx.compose.ui.AbsoluteAlignment
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
import com.example.projectcheva.screen.color


@Composable
fun beritaTerkiniList(){
    LazyRow(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        items(3) { index->
            beritaTerkini()
        }
    }
}

@Composable
fun beritaTerkini(){

    //set default font menjadi urbanist
    val fontFamily = FontFamily(
        Font(R.font.urbanist_black, FontWeight.Black),
        Font(R.font.urbanist_bold,FontWeight.Bold),
        Font(R.font.urbanist_extrabold,FontWeight.ExtraBold),
        Font(R.font.urbanist_extralight,FontWeight.ExtraLight),
        Font(R.font.urbanist_light,FontWeight.Light),
        Font(R.font.urbanist_medium,FontWeight.Medium),
        Font(R.font.urbanist_regular,FontWeight.Normal),
        Font(R.font.urbanist_semibold,FontWeight.SemiBold),
        Font(R.font.urbanist_thin,FontWeight.Thin),
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
                    .fillMaxWidth()
                    .height(310.dp)
                    .border(
                        width = 1.dp,                  // Width of the border
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp),           // Color of the border
                    )
                    .shadow(
                        elevation = 6.dp,
                        shape = RoundedCornerShape(8.dp)
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .size(310.dp)
                        .background(color = Color.White),

                ) {
                    Column(
                        modifier = Modifier
                            .align(Alignment.TopCenter),
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.berita_terkini),
                            contentDescription = "",
                            modifier = Modifier
                                .size(260.dp)
                                .padding(bottom = 55.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Warisan Budaya Tak Benda Kota Bandung Tingkat Provinsi dan Nasional 2018-2023",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .padding(bottom = 65.dp)
                                .padding(start = 25.dp)
                                .padding(end = 15.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Oleh",
                            color = Color.Black,
                            fontSize = 10.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Medium,
                            modifier = Modifier
                                .padding(start = 25.dp)
                                .padding(bottom = 45.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "Muhammad Nur Shodiq",
                            color = "#2f70b5".color,
                            fontSize = 10.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .padding(start = 50.dp)
                                .padding(bottom = 45.dp)
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 15.dp)
                            .align(Alignment.CenterEnd)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                       Box (
                           modifier = Modifier
                               .size(60.dp)
                               .padding(bottom = 41.dp)
                               .padding(end = 20.dp)
                               .border(
                                   width = 1.dp,
                                   color = "#2f70b5".color,
                                   shape = RoundedCornerShape(5.dp),
                               )
                               .align(AbsoluteAlignment.Right),
                       ){
                            Text(
                                text = "Kuliner",
                                color = "#2f70b5".color,
                                fontSize = 10.sp,
                                fontFamily = fontFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier
                                    .align(Alignment.Center)
                            )
                       }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 55.dp)
                            .padding(start = 70.dp)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 40.dp)
                        ) {
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 70.dp)
                                    .size(20.dp)
                                    .border(
                                        width = 1.dp,
                                        color = "#2f70b5".color,
                                        shape = RoundedCornerShape(5.dp)
                                    ),
                            ){
                                Text(
                                    text = "20/05/2024",
                                    color = "#2f70b5".color,
                                    fontSize = 10.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }
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
                                text= "Baca Selengkapnya",
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
fun beritaTerkiniPreview(){
    beritaTerkiniList()
}