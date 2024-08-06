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
fun DetailEvent(navController: NavController){

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
                            text = "EVENT",
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
                    painter = painterResource(id = R.drawable.event_rectangle),
                    contentDescription = null,
                    modifier = Modifier
                        .size(380.dp)
                        .align(Alignment.TopCenter)
                        .clip(RoundedCornerShape(6.dp))
                        .padding(bottom = 70.dp),
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.TopCenter)
                ) {
                    Text(
                        text = "Pameran dan Workshop Seni Batik Jabar",
                        color = Color.Black,
                        fontSize = 20.sp,
                        fontFamily = FontProvider.urbanist,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 300.dp)
                            .padding(horizontal = 24.dp)
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
                        text = "Bergabunglah dan berpartisipasi dalam pameran ;\n" +
                                "Indonesia Tourism & Trade Investment Expo 2024 “Prioritas Bandung”\n" +
                                "Tanggal : 18-21 Juli 2024, 07-10 November 2024 dan 05-08 Desember 2024\u2028Tempat : Cihampelas Walk Mall – Bandung\n" +
                                "Mari ikut andil dalam kesempatan terbaik untuk menjalin kerjasama baru di berbagai bidang usaha. Jangan lewatkan kesempatan terbaik untuk meningkatkan produk unggulan anda!!\n" +
                                "Info dan Contact Person :\n" +
                                "– Widya Zaskia 081380808278, \n" +
                                "– 081291541269  (CC)\n" +
                                "– furindo.arthamas12@gmail.com\n" +
                                "IG @furindo.arthamas\n" +
                                "Web : www.furindoarthamas.com",
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
                        text = "Deep learning adalah bentuk kecerdasan buatan yang meniru cara kerja otak manusia. Teknologi ini sangat efisien dalam memproses data mentah dan menciptakan pola untuk membantu pengambilan keputusan. Deep learning adalah bagian dari pembelajaran mesin yang memiliki jaringan sendiri. Teknologi ini memiliki kemampuan untuk mengidentifikasi pola dan informasi dari data yang tidak terstruktur atau tidak memiliki label tanpa memerlukan pengawasan manusia.\n" +
                                "Selain itu, Deep Learning adalah teknologi utama di balik mobil tanpa pengemudi.\n" +
                                "Teknologi ini juga menjadi landasan dari fungsi kontrol suara pada perangkat sehari-hari seperti ponsel pintar, tablet, televisi, dan speaker tanpa kabel",
                        color = Color.Black,
                        fontSize = 14.sp,
                        fontFamily = FontProvider.urbanist,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(top = 655.dp)
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
                            .padding(top = 900.dp)
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
                            .padding(top = 920.dp)
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
                            .padding(top = 1015.dp)
                            .padding(horizontal = 24.dp)
                    )


                }
            }

        }
    }
}

@Preview
@Composable
fun DetailEventPreview(){
    val context = LocalContext.current
    DetailEvent(navController = NavController(context))
}