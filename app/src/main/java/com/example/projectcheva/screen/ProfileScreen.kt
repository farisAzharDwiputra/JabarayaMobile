package com.example.projectcheva.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projectcheva.SignInMethod
import com.example.projectcheva.retrofit.AuthTokenManager
import com.example.projectcheva.retrofit.AuthViewModel
import com.example.projectcheva.retrofit.AuthViewModelFactory

@Composable
fun ProfileScreen(navController: NavController, signInMethod: SignInMethod) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel(factory = AuthViewModelFactory(context))
    val token = AuthTokenManager.getAuthToken(context)
    val isLoggedIn = token != null

    // Fetch user profile if logged in
    LaunchedEffect(signInMethod) {
        if (isLoggedIn) {
            viewModel.fetchUserProfile(signInMethod)
        }
    }
    // Conditional rendering based on authentication
    if (isLoggedIn) {
        LoggedInProfilePage(
            navController = navController,
            viewModel = viewModel,
            signInMethod = signInMethod
        )
    } else {
        NotLoggedInPage(navController)
    }
}

