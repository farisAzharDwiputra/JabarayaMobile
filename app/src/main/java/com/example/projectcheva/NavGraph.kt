package com.example.projectcheva

import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import com.example.projectcheva.fiturUtama.MapScreen
import com.example.projectcheva.fiturUtama.RecommendationsScreen
import com.example.projectcheva.fiturUtama.TravelPlanScreen
import com.example.projectcheva.retrofit.AuthTokenManager
import com.example.projectcheva.retrofit.AuthViewModel
import com.example.projectcheva.retrofit.AuthViewModelFactory
import com.example.projectcheva.screen.ArticleScreen
import com.example.projectcheva.screen.BeritaScreen
import com.example.projectcheva.screen.ChangePasswordScreen
import com.example.projectcheva.screen.DetailBerita
import com.example.projectcheva.screen.DetailBudaya
import com.example.projectcheva.screen.EditProfileScreen
import com.example.projectcheva.screen.FavoritScreen
import com.example.projectcheva.screen.ForgotPasswordScreen
import com.example.projectcheva.screen.HomeScreen
import com.example.projectcheva.screen.LoadingScreen
import com.example.projectcheva.screen.LoginScreen
import com.example.projectcheva.screen.MyApp
import com.example.projectcheva.screen.NotLoggedInPage
import com.example.projectcheva.screen.ProfileScreen
import com.example.projectcheva.screen.RegisScreen
import com.example.projectcheva.screen.SplashScreen
import com.facebook.CallbackManager
import com.google.android.gms.maps.model.LatLng

@OptIn(ExperimentalWearMaterialApi::class)
@Composable
fun NavGraph() {
    val callbackManager = CallbackManager.Factory.create()
    val navController = rememberNavController()
    val context = LocalContext.current
    val authToken = AuthTokenManager.getAuthToken(context)
    val isLoggedIn = authToken != null
    val authViewModel = viewModel<AuthViewModel>(factory = AuthViewModelFactory(context))

    // Retrieve the saved sign-in method from SharedPreferences
    val signInMethod = AuthTokenManager.getLoginMethod(context) ?: SignInMethod.Manual

    NavHost(
        navController = navController,
        startDestination = Screens.Beranda.route,
    ) {
        composable(Screens.Splash.route) {
            SplashScreen(onTimeout = {
                navController.navigate(Screens.Beranda.route) {
                    popUpTo(Screens.Splash.route) { inclusive = true }
                }
            })
        }
        composable(Screens.Login.route) {
            LoginScreen(navController, authViewModel)
        }
        composable(Screens.ForgotPassword.route) {
            ForgotPasswordScreen()
        }
        composable(Screens.Register.route) {
            RegisScreen(navController)
        }
        composable(Screens.Loading.route) {
            LoadingScreen()
        }
        composable(Screens.ChangePassword.route) {
            ChangePasswordScreen(
                navController,
                authViewModel,
                onPasswordChanged = { success, message ->
                    if (success) {
                        navController.popBackStack()
                        Toast.makeText(context, "Password changed successfully", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                    }
                }
            )
        }
        composable(Screens.Beranda.route) {
            BottomNavBarContent(navController) {
                HomeScreen(navController)
            }
        }
        composable(Screens.Favorit.route) {
            BottomNavBarContent(navController) {
                FavoritScreen(navController)
            }
        }
        composable(Screens.Berita.route) {
            BottomNavBarContent(navController) {
                BeritaScreen(navController)
            }
        }
        composable(Screens.DetailBerita.route) {
            BottomNavBarContent(navController) {
                DetailBerita(navController)
            }
        }
        composable(Screens.ListBerita.route) {
            listBerita(navController)
        }
        composable(Screens.Artikel.route) {
            BottomNavBarContent(navController) {
                ArticleScreen(navController)
            }
        }
        /*composable(Screens.DetailArticle.route) {
            DetailArticle(navController)
        }*/
        composable(Screens.DetailBudaya.route) {
            DetailBudaya(navController)
        }
        composable(Screens.Budaya.route) {
            BudayaScreen()
        }
        composable(Screens.Profile.route) {
            ProfileScreen(navController, signInMethod)
        }
        composable(Screens.EditProfile.route) {
            EditProfileScreen(navController, authViewModel, signInMethod)
        }
        composable(Screens.Plan.route){
            TravelPlanScreen(navController)
        }
        composable(Screens.NotLoggedPage.route){
            NotLoggedInPage(navController)
        }
        composable(Screens.facebook.route){
            MyApp(authViewModel, callbackManager)
        }
        composable(
            route = Screens.MapScreens.route,
            arguments = listOf(
                navArgument("initialPlace") { type = NavType.StringType },
                navArgument("destinationPlace") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val initialPlace = backStackEntry.arguments?.getString("initialPlace")?.let {
                LatLng(
                    it.split(",")[0].toDouble(),
                    it.split(",")[1].toDouble()
                )
            }
            val destinationPlace = backStackEntry.arguments?.getString("destinationPlace")?.let {
                LatLng(
                    it.split(",")[0].toDouble(),
                    it.split(",")[1].toDouble()
                )
            }
            MapScreen(
                initialPlace = initialPlace,
                destinationPlace = destinationPlace
            )
        }
        composable(Screens.recomend.route){
            RecommendationsScreen(context)
        }
    }
}

@Composable
fun BottomNavBarContent(navController: NavController, content: @Composable (Modifier) -> Unit) {
    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        content(Modifier.padding(paddingValues))
    }
}

@Preview
@Composable
fun NavPreview(){
    NavGraph()
}