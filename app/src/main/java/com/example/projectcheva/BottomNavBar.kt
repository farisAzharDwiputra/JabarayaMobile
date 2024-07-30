package com.example.projectcheva

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.projectcheva.screen.ArtikelScreen
import com.example.projectcheva.screen.BeritaScreen
import com.example.projectcheva.screen.HomeScreen
import com.example.projectcheva.screen.ProfileScreen
import com.example.projectcheva.screen.color
import com.google.firebase.auth.FirebaseAuth

//buttom bar
@Composable
fun BottomNavBar(){
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

    val navigationController= rememberNavController()
    val context = LocalContext.current.applicationContext
    val selected = remember{
        mutableStateOf(R.drawable.home_putih)
    }

    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier
                    .background(Color.Blue)

            ) {
                IconButton(
                    onClick = {
                              selected.value = R.drawable.home_putih
                        navigationController.navigate(Screens.Beranda.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.home_putih), contentDescription = null,
                            modifier =Modifier
                                .size(40.dp),
                            tint = if(selected.value == R.drawable.home_putih) "#3074b4".color else Color.White
                        )
                }
                IconButton(
                    onClick = {
                        selected.value = R.drawable.star_putih
                        navigationController.navigate(Screens.Favorit.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(painter = painterResource(id = R.drawable.star_putih), contentDescription = null,
                        modifier =Modifier
                            .size(40.dp),
                        tint = if(selected.value == R.drawable.star_putih) "#3074b4".color else Color.White
                    )
                }
                IconButton(
                    onClick = {
                        selected.value = R.drawable.news_putih
                        navigationController.navigate(Screens.Berita.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(painter = painterResource(id = R.drawable.news_putih), contentDescription = null,
                        modifier =Modifier
                            .size(40.dp),
                        tint = if(selected.value == R.drawable.news_putih) "#3074b4".color else Color.White
                    )
                }
                IconButton(
                    onClick = {
                        selected.value = R.drawable.page_putih
                        navigationController.navigate(Screens.Artikel.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(painter = painterResource(id = R.drawable.page_putih), contentDescription = null,
                        modifier =Modifier
                            .size(40.dp),
                        tint = if(selected.value == R.drawable.page_putih) "#3074b4".color else Color.White
                    )
                }
                IconButton(
                    onClick = {
                        selected.value = R.drawable.profile_putih
                        navigationController.navigate(Screens.Profile.screen){
                            popUpTo(0)
                        }
                    },
                    modifier = Modifier
                        .weight(1f)
                ) {
                    Icon(painter = painterResource(id = R.drawable.profile_putih), contentDescription = null,
                        modifier =Modifier
                            .size(40.dp),
                        tint = if(selected.value == R.drawable.profile_putih) "#3074b4".color else Color.White
                    )
                }
            }
        }
    ) {paddingValues ->
            NavHost(navController = navigationController,
                startDestination = Screens.Beranda.screen,
                modifier = Modifier
                    .padding(paddingValues)
            ){
                composable(Screens.Beranda.screen){ HomeScreen() }
                composable(Screens.Favorit.screen){ FavoritScreen() }
                composable(Screens.Berita.screen){ BeritaScreen() }
                composable(Screens.Artikel.screen){ ArtikelScreen() }
                composable(Screens.Profile.screen){ ProfileScreen(auth = FirebaseAuth.getInstance(), userData = null) {
                    
                } }
            }
    }
}

@Preview
@Composable
fun BottomNavBarPreview(){
    BottomNavBar()
}
