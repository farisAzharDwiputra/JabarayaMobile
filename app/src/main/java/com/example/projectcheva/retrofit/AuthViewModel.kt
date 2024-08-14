package com.example.projectcheva.retrofit

import android.app.Activity
import android.content.Context
import android.util.Log
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.exceptions.GetCredentialException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.projectcheva.BuildConfig
import com.example.projectcheva.ChangePasswordRequest
import com.example.projectcheva.GoogleLoginRequest
import com.example.projectcheva.Screens
import com.example.projectcheva.SignInMethod
import com.example.projectcheva.UpdateUserProfileRequest
import com.example.projectcheva.UserProfile
import com.example.projectcheva.UserProfileResponse
import com.example.projectcheva.retrofit.AuthTokenManager.getAuthToken
import com.example.projectcheva.retrofit.AuthTokenManager.sendTokenToBackend
import com.example.projectcheva.retrofit.RetrofitInstance.api
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import okio.IOException
import retrofit2.HttpException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

open class AuthViewModel(private val context: Context) : ViewModel() {

    private val repository = AuthRepository()
    private val _isLoading = MutableStateFlow(false)
    private val _loginState = MutableLiveData<Boolean>()
    val loginState: LiveData<Boolean> get() = _loginState
    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage
    private val _userProfile = MutableLiveData<UserProfile?>()
    open val userProfile: LiveData<UserProfile?> get() = _userProfile
    private val _authResult = MutableLiveData<Pair<Boolean, String?>>()
    val authResult: LiveData<Pair<Boolean, String?>> = _authResult

    fun login(email: String, password: String, onResult: (Boolean, String) -> Unit) {
        viewModelScope.launch {
            try {
                val response = repository.login(email, password)
                if (response?.status == true) {
                    val token = response.data.token
                    AuthTokenManager.saveAuthToken(context, token)
                    onResult(true, "Login successful")
                } else {
                    onResult(false, response?.message ?: "Login failed")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Login failed", e)
                onResult(false, "Login failed due to an error")
            }
        }
    }

    fun register(
        name: String,
        email: String,
        countryCode: String,
        phone: String,
        password: String,
        passwordConfirmation: String,
        onResult: (Boolean, String) -> Unit
    ) {
        Log.d("AuthViewModel", "Register function called with email: $email")
        viewModelScope.launch {
            try {
                // Format the phone number
                val formattedPhone = formatPhoneNumber(countryCode, phone)
                val response = repository.register(name, email, formattedPhone, password, passwordConfirmation)
                Log.d("AuthViewModel", "Register response received: $response")
                if (response?.status == true) {
                    val token = response.data.token
                    AuthTokenManager.saveAuthToken(context, token)
                    onResult(true, "Registration successful")
                } else {
                    onResult(false, response?.message ?: "Registration failed")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Registration failed", e)
                onResult(false, "Registration failed due to an error")
            }
        }
    }

    private fun formatPhoneNumber(countryCode: String, phoneNumber: String): String {
        return if (countryCode == "+62") {
            "0" + phoneNumber.removePrefix(countryCode)
        } else {
            countryCode + phoneNumber
        }
    }

    open fun getUserProfile(signInMethod: SignInMethod, onResult: (UserProfile?) -> Unit) {
        viewModelScope.launch {
            val token = getAuthToken(context)
            if (token != null) {
                try {
                    val response = repository.getUserProfile(token, signInMethod)
                    if (response != null) {
                        onResult(response.toUserProfile())
                    } else {
                        onResult(null)
                    }
                } catch (e: Exception) {
                    Log.e("AuthViewModel", "Error fetching user profile", e)
                    onResult(null)
                }
            } else {
                onResult(null)
            }
        }
    }

    fun updateUserProfile(request: UpdateUserProfileRequest, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val token = getAuthToken(context)
            token?.let {
                val success = repository.updateUserProfile(it, request)
                onResult(success)
            } ?: onResult(false)
        }
    }

    fun changePassword(
        currentPassword: String,
        newPassword: String,
        newPasswordConfirmation: String,
        onResult: (Boolean, String) -> Unit
    ) {
        viewModelScope.launch {
            val token = getAuthToken(context)
            if (token != null) {
                try {
                    val request = ChangePasswordRequest(currentPassword, newPassword, newPasswordConfirmation)
                    val success = repository.changePassword(token, request)
                    onResult(success, if (success) "Password changed successfully" else "Password change failed")
                } catch (e: Exception) {
                    Log.e("AuthViewModel", "Error changing password", e)
                    onResult(false, "Password change failed due to an error")
                }
            } else {
                onResult(false, "Authentication token not found")
            }
        }
    }

    fun googleSignIn() {
        viewModelScope.launch {
            Log.d("SIGN-IN", "Launching coroutine for Google Sign-In")
            _isLoading.value = true
            Log.d("SIGN-IN", "Loading indicator set to true")

            val googleIdOption = GetGoogleIdOption.Builder()
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId(BuildConfig.google_API)
                .build()
            Log.d("SIGN-IN", "GoogleIdOption built: $googleIdOption")

            val request = GetCredentialRequest.Builder()
                .addCredentialOption(googleIdOption)
                .build()
            Log.d("SIGN-IN", "GetCredentialRequest built: $request")

            try {
                val credentialManager = CredentialManager.create(context)
                Log.d("SIGN-IN", "CredentialManager created: $credentialManager")

                val result = credentialManager.getCredential(context, request)
                Log.d("SIGN-IN", "Credential retrieved: $result")

                handleGoogleSignIn(result)
                Log.d("SIGN-IN", "Handling Google Sign-In")
            } catch (e: GetCredentialException) {
                Log.e("SIGN-IN", "Error retrieving credentials: ${e.errorMessage}")
                _authResult.value = Pair(false, "Sign-In failed due to an error")
            } finally {
                _isLoading.value = false
                Log.d("SIGN-IN", "Loading indicator set to false")
            }
        }
    }

    private fun handleGoogleSignIn(result: GetCredentialResponse) {
        Log.d("SIGN-IN", "Handling Google Sign-In with result: $result")

        val credential = result.credential
        Log.d("SIGN-IN", "Credential obtained: $credential")

        if (credential is CustomCredential && credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
            Log.d("SIGN-IN", "Credential is CustomCredential and of Google ID Token type")

            try {
                val googleId = GoogleIdTokenCredential.createFrom(credential.data)
                Log.d("SIGN-IN", "Google ID Token created: $googleId")

                val token = googleId.idToken
                Log.d("SIGN-IN", "ID Token retrieved: $token")

                viewModelScope.launch {
                    Log.d("SIGN-IN", "Launching coroutine for authentication")

                    try {
                        val response = withContext(Dispatchers.IO) {
                            Log.d("SIGN-IN", "Authenticating with Google")
                            api.authenticateWithGoogle(GoogleLoginRequest(token))
                        }

                        Log.d("SIGN-IN", "Authentication response received: $response")

                        if (response.isSuccessful) {
                            Log.d("SIGN-IN", "Response is successful")

                            val authResponse = response.body()
                            Log.d("SIGN-IN", "AuthResponse body: $authResponse")

                            if (authResponse?.status == true) {
                                Log.d("SIGN-IN", "Authentication successful: ${authResponse.message}")
                                _authResult.value = Pair(true, "Sign-In successful")

                                // Save the token
                                Log.d("SIGN-IN", "Saving token: ${authResponse.data.token}")
                                AuthTokenManager.saveAuthToken(context, authResponse.data.token)

                                Log.d("SIGN-IN", "Token saved successfully.")
                            } else {
                                Log.e("SIGN-IN", "Authentication failed: ${authResponse?.message}")
                                _authResult.value = Pair(false, authResponse?.message ?: "Authentication failed")
                            }
                        } else {
                            Log.e("SIGN-IN", "Request failed: ${response.code()} ${response.message()}")
                            _authResult.value = Pair(false, "Request failed")
                        }
                    } catch (e: Exception) {
                        Log.e("SIGN-IN", "Error during authentication: ${e.message}")
                        _authResult.value = Pair(false, "Sign-In failed due to an error")
                    }
                }
            } catch (e: GoogleIdTokenParsingException) {
                Log.e("SIGN-IN", "Error parsing ID token: ${e.message}")
                _authResult.value = Pair(false, "Sign-In failed due to an error")
            }
        } else {
            Log.e("SIGN-IN", "Error: unrecognized custom credential type")
            _authResult.value = Pair(false, "Unrecognized credential type")
        }
    }

    // ViewModel Function
    fun facebookLogin(context: Context, callbackManager: CallbackManager) {
        viewModelScope.launch {
            try {
                Log.d("AuthViewModel", "Starting Facebook login...")
                val accessToken = facebookLoginAsync(context, callbackManager)
                accessToken?.let {
                    Log.d("AuthViewModel", "Facebook login successful, token: ${it.token}")
                    val response = sendTokenToBackend(it.token)
                    if (response.isSuccessful) {
                        Log.d("AuthViewModel", "Backend authentication successful")
                        response.body()?.let { loginResponse ->
                            AuthTokenManager.saveAuthToken(context, loginResponse.token.toString())
                            _loginState.postValue(true)
                            Log.d("AuthViewModel", "Auth token saved and login state updated")
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        Log.e("AuthViewModel", "Backend login failed: $errorBody")
                        _loginState.postValue(false)
                        _errorMessage.postValue("Backend login failed: $errorBody")
                    }
                } ?: run {
                    Log.d("AuthViewModel", "Facebook login cancelled")
                    _loginState.postValue(false)
                    _errorMessage.postValue("Login cancelled")
                }
            } catch (e: Exception) {
                Log.e("AuthViewModel", "Error during Facebook login: ${e.localizedMessage}")
                _loginState.postValue(false)
                _errorMessage.postValue(e.localizedMessage)
            }
        }
    }

    // Facebook Login Async Function
    private suspend fun facebookLoginAsync(context: Context, callbackManager: CallbackManager): AccessToken? {
        return suspendCancellableCoroutine { continuation ->
            Log.d("AuthViewModel", "facebookLoginAsync: Starting Facebook SDK login process")
            LoginManager.getInstance().logInWithReadPermissions(
                context as Activity,
                listOf("email", "public_profile")
            )
            LoginManager.getInstance().registerCallback(callbackManager,
                object : FacebookCallback<LoginResult> {
                    override fun onSuccess(loginResult: LoginResult) {
                        Log.d("AuthViewModel", "facebookLoginAsync: Facebook login successful")
                        continuation.resume(loginResult.accessToken)
                    }

                    override fun onCancel() {
                        Log.d("AuthViewModel", "facebookLoginAsync: Facebook login cancelled")
                        continuation.resume(null)
                    }

                    override fun onError(exception: FacebookException) {
                        Log.e("AuthViewModel", "facebookLoginAsync: Facebook login error: ${exception.localizedMessage}")
                        continuation.resumeWithException(exception)
                    }
                })
        }
    }

    open fun fetchUserProfile(signInMethod: SignInMethod) {
        Log.d("AuthViewModel", "Starting fetchUserProfile for $signInMethod")
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val authToken = getAuthToken(context)
                Log.d("fetchUserProfile", "Retrieved auth token: $authToken")

                if (authToken.isNullOrEmpty()) {
                    Log.e("fetchUserProfile", "Auth token is null or empty")
                    _userProfile.value = null
                    _authResult.value = Pair(false, "Auth token is missing")
                    return@launch
                }

                val authHeader = "Bearer $authToken"
                val profileResponse = when (signInMethod) {
                    SignInMethod.Manual -> {
                        Log.d("fetchUserProfile", "Fetching profile using Manual method")
                        api.getUserProfile(authHeader)
                    }
                    SignInMethod.Google -> {
                        Log.d("fetchUserProfile", "Fetching profile using Google method")
                        api.getUserProfileGoogle(authHeader)
                    }
                    SignInMethod.Facebook -> {
                        Log.d("fetchUserProfile", "Fetching profile using Facebook method")
                        TODO() // Implement Facebook fetch logic
                    }
                }

                Log.d("fetchUserProfile", "Profile response received: $profileResponse")
                _userProfile.value = profileResponse.toUserProfile()
                _authResult.value = Pair(true, null) // Success case

            } catch (e: HttpException) {
                Log.e("fetchUserProfile", "HTTP error: ${e.code()} ${e.message()}", e)
                _userProfile.value = null
                _authResult.value = Pair(false, "HTTP error: ${e.message()}")
            } catch (e: IOException) {
                Log.e("fetchUserProfile", "Network error: ${e.message}", e)
                _userProfile.value = null
                _authResult.value = Pair(false, "Network error: ${e.message}")
            } catch (e: Exception) {
                Log.e("fetchUserProfile", "Unexpected error: ${e.message}", e)
                _userProfile.value = null
                _authResult.value = Pair(false, "Unexpected error: ${e.message}")
            } finally {
                _isLoading.value = false
                Log.d("fetchUserProfile", "Ending fetchUserProfile with authResult: ${_authResult.value}")
            }
        }
    }

    private fun UserProfileResponse.toUserProfile(): UserProfile {
        return UserProfile().apply {
            this.id = this@toUserProfile.data?.user?.id ?: 0
            this.name = this@toUserProfile.data?.user?.name
            this.email = this@toUserProfile.data?.user?.email
            this.phone = this@toUserProfile.data?.user?.phone
            this.avatar = this@toUserProfile.data?.user?.avatar
            this.email_verified_at = this@toUserProfile.data?.user?.email_verified_at
            this.role = this@toUserProfile.data?.user?.role
            this.created_at = this@toUserProfile.data?.user?.created_at
            this.updated_at = this@toUserProfile.data?.user?.updated_at
        }
    }

    fun logout(navController: NavController) {
        viewModelScope.launch {

            Log.d("Logout", "Starting logout process")
            // Log the authResult before starting the logout process
            Log.d("Logout", "Before logout, authResult: ${_authResult.value}")

            try {
                // Navigate to HomeScreen
                Log.d("Logout","Navigate to HomeScreen")
                navController.navigate(Screens.Beranda.route)

                // Clear authentication token
                AuthTokenManager.clearAuthToken(context)
                Log.d("Logout", "Authentication token cleared.")

                // Clear user data
                clearUserData()
                Log.d("Logout", "User data cleared.")

                // Clear login method
                clearLoginMethod()
                Log.d("Logout", "Login method cleared.")

                // Clear authResult
                _authResult.value = null
                Log.d("Logout", "AuthResult cleared.")

                // Ensure that authResult is cleared before notifying success
                delay(500) // Adjust delay if necessary

                // Verify authResult after delay
                Log.d("Logout", "Post-delay AuthResult: ${_authResult.value}")

                // Notify success
                _authResult.value = Pair(true, "Successfully logged out")
            } catch (e: Exception) {
                Log.e("Logout", "Error during logout: ${e.message}")
                _authResult.value = Pair(false, "Logout failed due to an error")
            }
        }
    }

    private fun clearUserData() {
        try {
            val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().clear().apply()
            Log.d("Logout", "User data cleared.")
        } catch (e: Exception) {
            Log.e("Logout", "Error clearing user data: ${e.message}", e)
        }
    }

    private fun clearLoginMethod() {
        try {
            val sharedPreferences = context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
            sharedPreferences.edit().remove("login_method_key").apply()
            Log.d("Logout", "Login method cleared.")
        } catch (e: Exception) {
            Log.e("Logout", "Error clearing login method: ${e.message}", e)
        }
    }

    fun clearAuthResult() {
        _authResult.value = null
    }
}
