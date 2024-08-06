package com.example.projectcheva.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectcheva.FontProvider

@Composable
fun FavoritScreen(navController: NavController) {

    Box(modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Favorit",
                color = Color.Black,
                fontSize = 16.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.Normal
            )

        }
    }
}

@Preview
@Composable
fun FavoritPreview(){
    val context = LocalContext.current
    FavoritScreen(navController = NavController(context))
}