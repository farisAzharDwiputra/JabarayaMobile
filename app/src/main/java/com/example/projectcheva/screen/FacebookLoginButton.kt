package com.example.projectcheva.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.projectcheva.retrofit.AuthViewModel
import com.facebook.CallbackManager

@Composable
fun MyApp(
    authViewModel: AuthViewModel,
    callbackManager: CallbackManager
) {
    val loginState by authViewModel.loginState.observeAsState(false)
    val errorMessage by authViewModel.errorMessage.observeAsState(null)
    val context = LocalContext.current

    Scaffold(
        content = { paddingValues ->
            if (loginState) {
                Text(text = "Welcome, you are logged in!", modifier = Modifier.padding(paddingValues))
            } else {
                FacebookLoginButton(
                    onLoginClick = {
                        // Call ViewModel's facebookLogin function with CallbackManager
                        authViewModel.facebookLogin(context, callbackManager)
                    },
                    modifier = Modifier.padding(paddingValues)
                )
            }
            errorMessage?.let { message ->
                Text(text = "Error: $message", color = Color.Red, modifier = Modifier.padding(paddingValues))
            }
        }
    )
}

@Composable
fun FacebookLoginButton(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(onClick = onLoginClick, modifier = modifier) {
        Text("Login with Facebook")
    }
}
