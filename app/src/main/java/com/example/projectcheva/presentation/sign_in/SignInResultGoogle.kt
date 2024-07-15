package com.example.projectcheva.presentation.sign_in

data class SignInResultGoogle(
    val data: UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureURL: String?
)
