package com.example.projectcheva.retrofit

import android.util.Log
import com.example.projectcheva.AuthResponse
import com.example.projectcheva.ChangePasswordRequest
import com.example.projectcheva.LoginRequest
import com.example.projectcheva.RegisterRequest
import com.example.projectcheva.SignInMethod
import com.example.projectcheva.UpdateUserProfileRequest
import com.example.projectcheva.UserProfileResponse

class AuthRepository {

    private val api = RetrofitInstance.api

    suspend fun login(email: String, password: String): AuthResponse? {
        return try {
            api.login(LoginRequest(email, password))
        } catch (e: Exception) {
            Log.e("AuthRepository", "Login failed", e)
            null
        }
    }

    suspend fun register(name: String, email: String, phone: String, password: String, passwordConfirmation: String): AuthResponse? {
        return try {
            api.register(RegisterRequest(name, email, phone, password, passwordConfirmation))
        } catch (e: Exception) {
            Log.e("AuthRepository", "Register failed", e)
            null
        }
    }

    suspend fun getUserProfile(token: String, signInMethod: SignInMethod): UserProfileResponse? {
        return try {
            when (signInMethod) {
                SignInMethod.Manual -> api.getUserProfile("Bearer $token")
                SignInMethod.Google -> api.getUserProfileGoogle("Bearer $token")
                SignInMethod.Facebook -> TODO()
            }
        } catch (e: Exception) {
            Log.e("AuthRepository", "Get user profile failed", e)
            null
        }
    }

    suspend fun updateUserProfile(token: String, request: UpdateUserProfileRequest): Boolean {
        return try {
            api.updateUserProfile("Bearer $token", request)
            true
        } catch (e: Exception) {
            Log.e("AuthRepository", "Update user profile failed", e)
            false
        }
    }

    suspend fun changePassword(token: String, request: ChangePasswordRequest): Boolean {
        return try {
            api.changePassword("Bearer $token", request)
            true
        } catch (e: Exception) {
            Log.e("AuthRepository", "Change password failed", e)
            false
        }
    }

}
