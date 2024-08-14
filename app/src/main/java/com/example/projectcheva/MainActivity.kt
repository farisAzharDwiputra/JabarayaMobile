package com.example.projectcheva

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import com.example.projectcheva.retrofit.AuthViewModel
import com.facebook.CallbackManager
import com.facebook.FacebookSdk
import com.google.android.libraries.places.api.Places

class MainActivity : ComponentActivity() {
    private val callbackManager = CallbackManager.Factory.create()
    private val authViewModel: AuthViewModel by viewModels()

    private val facebookLoginResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            callbackManager.onActivityResult(result.resultCode, result.resultCode, result.data)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize Google Places and Facebook SDK
        Places.initialize(applicationContext, "AIzaSyBsZ2s1Dcx5Jx9mDLQ6_kNslS6z0f9V4dQ")
        FacebookSdk.sdkInitialize(this)

        setContent {
            NavGraph()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }
}

