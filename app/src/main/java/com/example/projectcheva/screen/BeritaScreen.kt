package com.example.projectcheva.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectcheva.FontProvider
import com.example.projectcheva.R
import com.example.projectcheva.Screens

@Composable
fun BeritaScreen(navController: NavController) {
    Column( modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ){
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .padding(horizontal = 24.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                ) {
                    Text(
                        text = "BERITA",
                        color = Color.Black,
                        fontSize = 32.sp,
                        fontFamily = FontProvider.urbanist,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Berita Terkini di Bandung Raya",
                        color = Color.Black,
                        fontSize = 22.sp,
                        fontFamily = FontProvider.urbanist,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                    )
                }
                Spacer(modifier = Modifier.height(18.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .padding(horizontal = 40.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.TopCenter)
                            .padding(bottom = 5.dp)
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo_jabaraya),
                            contentDescription = "",
                            modifier = Modifier
                                .size(30.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
            }
        }
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
                        width = 1.dp,
                        color = Color.LightGray,
                        shape = RoundedCornerShape(8.dp),
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
                            fontFamily = FontProvider.urbanist,
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
                            fontFamily = FontProvider.urbanist,
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
                            fontFamily = FontProvider.urbanist,
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
                                fontFamily = FontProvider.urbanist,
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
                                    fontFamily = FontProvider.urbanist,
                                    fontWeight = FontWeight.SemiBold,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .align(Alignment.TopCenter)
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        OutlinedButton(
                            onClick = {
                                navController.navigate(Screens.DetailBerita.route)
                            },
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
                                fontFamily = FontProvider.urbanist,
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
fun BeritaScreenPreview(){
    val context = LocalContext.current
    BeritaScreen(navController = NavController(context))
}