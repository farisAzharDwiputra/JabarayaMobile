package com.example.projectcheva.presentation.sign_in

data class SignInState(
    val isSignInSuccesfull: Boolean = false,
    val signInError: String? = null,
    val isFacebookSignInSuccessful: Boolean = false
)
