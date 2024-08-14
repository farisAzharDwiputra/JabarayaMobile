package com.example.projectcheva.retrofit

import android.content.Context
import com.example.projectcheva.LoginResponse
import com.example.projectcheva.SignInMethod
import com.example.projectcheva.TokenRequest
import retrofit2.Response

object AuthTokenManager {
    private const val PREF_NAME = "auth_pref"
    private const val KEY_AUTH_TOKEN = "auth_token"
    private const val KEY_LOGIN_METHOD = "login_method"

    // Auth Token Management
    fun saveAuthToken(context: Context, token: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString(KEY_AUTH_TOKEN, token)
            apply()
        }
    }

    fun getAuthToken(context: Context): String? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_AUTH_TOKEN, null)
    }

    fun clearAuthToken(context: Context) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            remove(KEY_AUTH_TOKEN)
            apply()
        }
    }

    // Login Method Management
    fun saveLoginInMethod(context: Context, method: SignInMethod) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(prefs.edit()) {
            putString(KEY_LOGIN_METHOD, method.name)
            apply()
        }
    }

    fun getLoginMethod(context: Context): SignInMethod? {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val methodName = prefs.getString(KEY_LOGIN_METHOD, null)
        return methodName?.let { SignInMethod.valueOf(it) }
    }

    suspend fun sendTokenToBackend(facebookToken: String): Response<LoginResponse> {
        val tokenRequest = TokenRequest(facebookToken)
        return RetrofitInstance.api.authenticateWithFacebook(tokenRequest)
    }
}
