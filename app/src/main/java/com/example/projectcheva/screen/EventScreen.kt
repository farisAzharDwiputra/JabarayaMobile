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
import androidx.compose.foundation.lazy.LazyColumn
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
import com.example.projectcheva.R


@Composable
fun EventScreen(){
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


    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        item{
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
                    ){
                        Image(
                            painter = painterResource(id = R.drawable.icon_arrow),
                            contentDescription = "",
                            modifier = Modifier
                                .size(40.dp)
                                .align(Alignment.TopStart)
                        )

                        Text(
                            text = "EVENT",
                            color = Color.Black,
                            fontSize = 32.sp,
                            fontFamily = fontFamily,
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
                            text = "Event Seru di Bandung Raya",
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
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
                    /*   Box(
                           modifier = Modifier
                               .align(AbsoluteAlignment.TopLeft)
                       ) {
                           Image(
                               painter = painterResource(id = R.drawable.icon_vector)
                               , contentDescription = "",
                               modifier = Modifier
                                   .size(120.dp)
                                   .align(Alignment.TopStart)
                           )
                       }
                       Box (
                           modifier = Modifier
                               .fillMaxWidth()
                               .align(AbsoluteAlignment.TopRight)
                       ){
                           Image(
                               painter = painterResource(id = R.drawable.icon_vector)
                               , contentDescription = "",
                               modifier = Modifier
                                   .size(120.dp)
                                   .align(Alignment.TopEnd)
                                   .padding(bottom = 80.dp)
                           )
                       }
                   }*/
                    //card1
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.TopCenter),
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.gambar_aset1),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(265.dp)
                                            .padding(bottom = 55.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
                                        .align(Alignment.BottomCenter)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = "Indonesia Tourism & Trade Investment Expo 2024 “Prioritas Bandung”",
                                        color = Color.Black,
                                        fontSize = 14.sp,
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(bottom = 65.dp)
                                            .padding(start = 30.dp)
                                            .padding(end = 15.dp)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
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
                                            text = "Umum",
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
                                        .padding(bottom = 50.dp)
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
                    //card 2
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.TopCenter),
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.gambar_aset1),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(265.dp)
                                            .padding(bottom = 55.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
                                        .align(Alignment.BottomCenter)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = "Indonesia Tourism & Trade Investment Expo 2024 “Prioritas Bandung”",
                                        color = Color.Black,
                                        fontSize = 14.sp,
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(bottom = 65.dp)
                                            .padding(start = 30.dp)
                                            .padding(end = 15.dp)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
                                        .align(Alignment.CenterEnd)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Box(
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
                                    ) {
                                        Text(
                                            text = "Umum",
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
                                        .padding(bottom = 50.dp)
                                        .padding(start = 70.dp)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 40.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 70.dp)
                                                .size(20.dp)
                                                .border(
                                                    width = 1.dp,
                                                    color = "#2f70b5".color,
                                                    shape = RoundedCornerShape(5.dp)
                                                ),
                                        ) {
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
                                            text = "Baca Selengkapnya",
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
                    //card3
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.TopCenter),
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.gambar_aset1),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(265.dp)
                                            .padding(bottom = 55.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
                                        .align(Alignment.BottomCenter)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = "Indonesia Tourism & Trade Investment Expo 2024 “Prioritas Bandung”",
                                        color = Color.Black,
                                        fontSize = 14.sp,
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(bottom = 65.dp)
                                            .padding(start = 30.dp)
                                            .padding(end = 15.dp)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
                                        .align(Alignment.CenterEnd)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Box(
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
                                    ) {
                                        Text(
                                            text = "Umum",
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
                                        .padding(bottom = 50.dp)
                                        .padding(start = 70.dp)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 40.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 70.dp)
                                                .size(20.dp)
                                                .border(
                                                    width = 1.dp,
                                                    color = "#2f70b5".color,
                                                    shape = RoundedCornerShape(5.dp)
                                                ),
                                        ) {
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
                                            text = "Baca Selengkapnya",
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
                    //card 4
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.TopCenter),
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.gambar_aset1),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(265.dp)
                                            .padding(bottom = 55.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
                                        .align(Alignment.BottomCenter)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = "Indonesia Tourism & Trade Investment Expo 2024 “Prioritas Bandung”",
                                        color = Color.Black,
                                        fontSize = 14.sp,
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(bottom = 65.dp)
                                            .padding(start = 30.dp)
                                            .padding(end = 15.dp)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
                                        .align(Alignment.CenterEnd)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Box(
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
                                    ) {
                                        Text(
                                            text = "Umum",
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
                                        .padding(bottom = 50.dp)
                                        .padding(start = 70.dp)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 40.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 70.dp)
                                                .size(20.dp)
                                                .border(
                                                    width = 1.dp,
                                                    color = "#2f70b5".color,
                                                    shape = RoundedCornerShape(5.dp)
                                                ),
                                        ) {
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
                                            text = "Baca Selengkapnya",
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
                    //card5
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.TopCenter),
                                ) {
                                    Image(
                                        painter = painterResource(id = R.drawable.gambar_aset1),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(265.dp)
                                            .padding(bottom = 55.dp)
                                            .align(Alignment.CenterHorizontally)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
                                        .align(Alignment.BottomCenter)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Text(
                                        text = "Indonesia Tourism & Trade Investment Expo 2024 “Prioritas Bandung”",
                                        color = Color.Black,
                                        fontSize = 14.sp,
                                        fontFamily = fontFamily,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier
                                            .padding(bottom = 65.dp)
                                            .padding(start = 30.dp)
                                            .padding(end = 15.dp)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
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
                                        .padding(bottom = 10.dp)
                                        .align(Alignment.CenterEnd)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Box(
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
                                    ) {
                                        Text(
                                            text = "Umum",
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
                                        .padding(bottom = 50.dp)
                                        .padding(start = 70.dp)
                                ) {
                                    Spacer(modifier = Modifier.weight(1f))
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(start = 40.dp)
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 70.dp)
                                                .size(20.dp)
                                                .border(
                                                    width = 1.dp,
                                                    color = "#2f70b5".color,
                                                    shape = RoundedCornerShape(5.dp)
                                                ),
                                        ) {
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
                                            text = "Baca Selengkapnya",
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
        }
    }
}

@Preview
@Composable
fun EventScreenPreview(){
    EventScreen()
}