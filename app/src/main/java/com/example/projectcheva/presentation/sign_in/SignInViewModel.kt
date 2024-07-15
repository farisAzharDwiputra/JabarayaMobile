package com.example.projectcheva.presentation.sign_in

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel: ViewModel() {

    private val auth = FirebaseAuth.getInstance()
    private val _state = MutableStateFlow(SignInState())
    val state = _state.asStateFlow()

    fun onSignInResult(result: SignInResultGoogle) {
        _state.update { it.copy(
            isSignInSuccesfull = result.data != null,
            signInError = result.errorMessage
        ) }
    }
    fun onFacebookSignInSuccess() {
        _state.value = _state.value.copy(isFacebookSignInSuccessful = true)
    }
    fun resetState() {
        _state.update { SignInState() }
    }
}