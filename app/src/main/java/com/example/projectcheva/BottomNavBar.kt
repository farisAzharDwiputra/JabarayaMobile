package com.example.projectcheva

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projectcheva.screen.color

@Composable
fun BottomNavBar(navController: NavController) {
    LocalContext.current
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    var selected by remember { mutableStateOf(R.drawable.home_putih) }

    LaunchedEffect(currentRoute) {
        selected = when (currentRoute) {
            Screens.Beranda.route -> R.drawable.home_putih
            Screens.Favorit.route -> R.drawable.star_putih
            Screens.Berita.route -> R.drawable.news_putih
            Screens.Artikel.route -> R.drawable.page_putih
            Screens.Profile.route -> R.drawable.profile_putih
            else -> R.drawable.home_putih
        }
    }

    Surface(
        color = Color(0xFF68B1FF),
        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
        modifier = Modifier
    ) {
        BottomAppBar(
            modifier = Modifier,
            containerColor = Color.Transparent,
            contentColor = Color.White,
            tonalElevation = BottomAppBarDefaults.ContainerElevation,
            contentPadding = BottomAppBarDefaults.ContentPadding,
            content = {
                IconButton(
                    onClick = {
                        navController.navigate(Screens.Beranda.route) {
                            popUpTo(Screens.Login.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.home_putih),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = if (selected == R.drawable.home_putih) "#3074b4".color else Color.White
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate(Screens.Favorit.route) {
                            popUpTo(Screens.Login.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.star_putih),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = if (selected == R.drawable.star_putih) "#3074b4".color else Color.White
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate(Screens.Berita.route) {
                            popUpTo(Screens.Login.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.news_putih),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = if (selected == R.drawable.news_putih) "#3074b4".color else Color.White
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate(Screens.Artikel.route) {
                            popUpTo(Screens.Login.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.page_putih),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = if (selected == R.drawable.page_putih) "#3074b4".color else Color.White
                    )
                }
                IconButton(
                    onClick = {
                        navController.navigate(Screens.Profile.route) {
                            popUpTo(Screens.Login.route) { inclusive = true }
                        }
                    },
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.profile_putih),
                        contentDescription = null,
                        modifier = Modifier.size(40.dp),
                        tint = if (selected == R.drawable.profile_putih) "#3074b4".color else Color.White
                    )
                }
            }
        )
    }
}

@Preview
@Composable
fun BottomNavBarPreview() {
    BottomNavBar(rememberNavController())
}