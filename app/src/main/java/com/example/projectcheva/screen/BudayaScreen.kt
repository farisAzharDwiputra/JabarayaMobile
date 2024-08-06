package com.example.projectcheva

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BudayaScreen(){

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
                            text = "BUDAYA",
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
                            text = "Budaya di Bandung Raya",
                            color = Color.Black,
                            fontSize = 22.sp,
                            fontFamily = FontProvider.urbanist,
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
                                .height(310.dp)
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Text(
                                    text= "Kuliner di Bandung",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = FontProvider.urbanist,
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
                                        painter = painterResource(id = R.drawable.batagor_foto),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(280.dp)
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
                    //card 2
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(310.dp)
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Text(
                                    text= "Tari di Bandung",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = FontProvider.urbanist,
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
                                            .size(280.dp)
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

                    //card3
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(310.dp)
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Text(
                                    text= "Wayang di Bandung",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = FontProvider.urbanist,
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
                                        painter = painterResource(id = R.drawable.gambar_wayang),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(280.dp)
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
                    //card 4
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(310.dp)
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Text(
                                    text= "Musik di Bandung",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = FontProvider.urbanist,
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
                                        painter = painterResource(id = R.drawable.gambar_musik),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(280.dp)
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

                    //card5
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(310.dp)
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Text(
                                    text= "Alat Musik di Bandung",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = FontProvider.urbanist,
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
                                        painter = painterResource(id = R.drawable.alat_musik),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(280.dp)
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
                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(310.dp)
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Text(
                                    text= "Senjata Adat di Bandung",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = FontProvider.urbanist,
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
                                        painter = painterResource(id = R.drawable.senjata_adat),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(280.dp)
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

                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(310.dp)
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Text(
                                    text= "Museum di Bandung",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = FontProvider.urbanist,
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
                                        painter = painterResource(id = R.drawable.museum_bandung),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(280.dp)
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

                    Spacer(modifier = Modifier.height(20.dp))
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp)
                        .align(Alignment.CenterHorizontally)
                    ) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Card(
                            modifier = Modifier
                                .height(310.dp)
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
                                    .size(320.dp)
                                    .background(color = Color.White),
                            ) {
                                Text(
                                    text= "Rumah Adat di Bandung",
                                    color = Color.Black,
                                    fontSize = 16.sp,
                                    fontFamily = FontProvider.urbanist,
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
                                        painter = painterResource(id = R.drawable.rumah_adat),
                                        contentDescription = "",
                                        modifier = Modifier
                                            .size(280.dp)
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
        }
    }
}

@Preview
@Composable
fun BudayaScreenPreview(){
    BudayaScreen()
}