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
fun DetailBudaya(navController: NavController){

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
                }
            }
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.batagor_foto),
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
                    ) {
                        Text(
                            text = "Batagor Khas Bandung",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .padding(top = 300.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "By Tiyas Insania D.",
                            color = Color.Black,
                            fontSize = 16.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 350.dp)
                                .padding(horizontal = 24.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Definisi",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 380.dp)
                                .padding(horizontal = 24.dp)
                        )
                        Text(
                            text = "Batagor (singkatan dari Baso, Tahu, Goreng), adalah jajanan khas Bandung[1] yang kini sudah dikenal hampir di seluruh wilayah Indonesia.\n" +
                                    "Secara umum, batagor dibuat dari tahu yang dilembutkan dan diisi dengan adonan berbahan ikan tenggiri dan tepung tapioka lalu dibentuk menyerupai bola yang digoreng dalam minyak panas selama beberapa menit hingga matang. Variasi lainnya yaitu siomay, digoreng dan dihidangkan bersama batagor dan dikombinasikan dengan bumbu kacang, kecap manis, sambal, dan air perasan jeruk nipis sebagai pelengkap.[2] Saat ini, batagor tidak hanya berbentuk bulat saja, namun terdapat varian batagor yang dibalut dengan kulit pangsit yang digoreng. Batagor dengan varian ini banyak ditemukan di Jawa Barat, khususnya Bandung. Bahkan, penyajian batagor tidak hanya dengan bumbu kacang yang khasnya saja, tetapi ada juga batagor yang disajikan dengan kuah seperti bakso. Batagor jenis ini dikenal dengan nama batagor kuah. Beberapa pedagang juga ada yang menyajikan batagor dengan tambahan irisan timun yang menambah cita rasa batagor semakin enak.",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 400.dp)
                                .padding(horizontal = 24.dp)
                        )
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Sejarah",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 770.dp)
                                .padding(horizontal = 24.dp)
                        )
                        Text(
                            text = "Batagor (singkatan dari Baso, Tahu, Goreng), adalah jajanan khas Bandung[1] yang kini sudah dikenal hampir di seluruh wilayah Indonesia.\n" +
                                    "Secara umum, batagor dibuat dari tahu yang dilembutkan dan diisi dengan adonan berbahan ikan tenggiri dan tepung tapioka lalu dibentuk menyerupai bola yang digoreng dalam minyak panas selama beberapa menit hingga matang. Variasi lainnya yaitu siomay, digoreng dan dihidangkan bersama batagor dan dikombinasikan dengan bumbu kacang, kecap manis, sambal, dan air perasan jeruk nipis sebagai pelengkap.[2] Saat ini, batagor tidak hanya berbentuk bulat saja, namun terdapat varian batagor yang dibalut dengan kulit pangsit yang digoreng. Batagor dengan varian ini banyak ditemukan di Jawa Barat, khususnya Bandung. Bahkan, penyajian batagor tidak hanya dengan bumbu kacang yang khasnya saja, tetapi ada juga batagor yang disajikan dengan kuah seperti bakso. Batagor jenis ini dikenal dengan nama batagor kuah. Beberapa pedagang juga ada yang menyajikan batagor dengan tambahan irisan timun yang menambah cita rasa batagor semakin enak.",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 790.dp)
                                .padding(horizontal = 24.dp)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Baca Juga",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 1155.dp)
                                .padding(horizontal = 24.dp)
                        )
                        Text(
                            text = "- Cara Buat Batagor ala ahli kuliner Humam\n" +
                                    "- Rekomendasi Tempat Batagor Terenak di Dekat Kost rimba\n" +
                                    "- Tips Menghindari Batagor Boraks ala \n" +
                                    "- Tutorial Cara Membuat Batagor Terenak ala Aufa",
                            fontSize = 14.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.Normal,
                            color = "#2596be".color,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 1180.dp)
                                .padding(horizontal = 24.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Rekomendasi Tempat",
                            color = Color.Black,
                            fontSize = 14.sp,
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .align(Alignment.TopStart)
                                .padding(top = 1280.dp)
                                .padding(horizontal = 24.dp)
                        )


                    }
                }

        }
    }
}

@Preview
@Composable
fun DetailBudayaPreview(){
    val context = LocalContext.current
    DetailBudaya(navController = NavController(context))
}