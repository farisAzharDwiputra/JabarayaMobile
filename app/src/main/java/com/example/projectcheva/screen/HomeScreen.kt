package com.example.projectcheva.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.projectcheva.R
import com.example.projectcheva.beritaTerkiniList
import com.example.projectcheva.listDaftarIsiBudaya
import com.example.projectcheva.listIsiArtikel
import com.example.projectcheva.listIsiEvent
import com.example.projectcheva.topBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(){


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

    LazyColumn (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            //topbar
            topBar()
        }

        item {
            //main content
            Spacer(modifier = Modifier.height(28.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        text = "Buat Rencana Perjalanan Anda ",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(bottom = 10.dp)
                    )
                    Button(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .fillMaxWidth()
                            .size(50.dp)
                            .padding(horizontal = 70.dp)
                            .align(Alignment.CenterHorizontally),
                        shape = RoundedCornerShape(8.dp),
                        colors = ButtonDefaults.buttonColors("#2f70b5".color)
                    ) {
                        Text(
                            text = "Buat Rencana Perjalanan Kamu disini?",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                        )
                    }
                }
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Berita Terkini",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold
                )
                Column {
                    beritaTerkiniList()

                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .align(AbsoluteAlignment.Right)
                    ) {
                        Text(
                            text = "Selengkapnya.......",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                //event
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Artikel",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column {
                    listIsiEvent()

                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .align(AbsoluteAlignment.Right)
                    ) {
                        Text(
                            text = "Selengkapnya.......",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                //artikel
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Event",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column {
                    listIsiArtikel()

                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .align(AbsoluteAlignment.Right)
                    ) {
                        Text(
                            text = "Selengkapnya.......",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
                //berita
                Spacer(modifier = Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .padding(horizontal = 24.dp)
                ) {
                    Text(
                        text = "Budaya",
                        color = Color.Black,
                        fontSize = 16.sp,
                        fontFamily = fontFamily,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column {
                    listDaftarIsiBudaya()

                    Spacer(modifier = Modifier.height(10.dp))
                    Column(
                        modifier = Modifier
                            .align(AbsoluteAlignment.Right)
                    ) {
                        Text(
                            text = "Selengkapnya.......",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = fontFamily,
                            fontWeight = FontWeight.Normal
                        )
                    }
                }
            }
        }
    }
}


@Preview
@Composable
fun HomeScreenPreview(){
    Surface {
        HomeScreen()
    }
}