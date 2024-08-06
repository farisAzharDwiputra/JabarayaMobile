@file:JvmName("BudayaScreenKt")

package com.example.projectcheva.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@Composable
fun DetailBerita(navController: NavController){

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                modifier = Modifier
                    .padding(horizontal = 24.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp),
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
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopCenter
            ) {
                Image(
                    painter = painterResource(id = R.drawable.berita_terkini),
                    contentDescription = null,
                    modifier = Modifier
                        .size(380.dp)
                        .align(Alignment.TopCenter)
                        .clip(RoundedCornerShape(6.dp))
                        .padding(bottom = 70.dp),
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Center)
                ) {
                    Text(
                        text = "Warisan Budaya Tak Benda Kota Bandung Tingkat Provinsi dan Nasional 2018-2023",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = FontProvider.urbanist,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(AbsoluteAlignment.CenterRight)
                            .padding(top = 300.dp)
                            .padding(horizontal = 24.dp)
                    )
                }
            }

        }
    }
}

@Preview
@Composable
fun DetailBeritaPreview(){
    val context = LocalContext.current
    DetailBerita(navController = NavController(context))
}