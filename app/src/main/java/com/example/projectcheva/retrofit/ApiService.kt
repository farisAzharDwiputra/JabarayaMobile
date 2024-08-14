package com.example.projectcheva.retrofit

import com.example.projectcheva.AuthResponse
import com.example.projectcheva.ChangePasswordRequest
import com.example.projectcheva.DestinationListResponse
import com.example.projectcheva.DestinationResponse
import com.example.projectcheva.GoogleLoginRequest
import com.example.projectcheva.LoginRequest
import com.example.projectcheva.LoginResponse
import com.example.projectcheva.RecommendationsResponse
import com.example.projectcheva.RegisterRequest
import com.example.projectcheva.TokenRequest
import com.example.projectcheva.TravelPlanInput
import com.example.projectcheva.TravelPlanListResponse
import com.example.projectcheva.TravelPlanResponse
import com.example.projectcheva.UpdateUserProfileRequest
import com.example.projectcheva.UserProfileResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): AuthResponse

    @POST("api/auth/register")
    suspend fun register(@Body registerRequest: RegisterRequest): AuthResponse

    // Method to get user profile with an authorization header
    @GET("api/user")
    suspend fun getUserProfile(@Header("Authorization") authHeader: String): UserProfileResponse

    // Method to get user profile with an authorization header for Google sign-in
    @GET("api/user")
    suspend fun getUserProfileGoogle(@Header("Authorization") authHeader: String): UserProfileResponse

    @POST("api/user/?_method=PUT")
    suspend fun updateUserProfile(
        @Header("Authorization") authHeader: String,
        @Body updateRequest: UpdateUserProfileRequest
    )

    @POST("api/user/change-password")
    suspend fun changePassword(
        @Header("Authorization") authHeader: String,
        @Body changePasswordRequest: ChangePasswordRequest
    )

    @POST("api/login/google/callback")
    suspend fun authenticateWithGoogle(@Body token: GoogleLoginRequest): Response<AuthResponse>

    @POST("login/facebook/callback")
    suspend fun authenticateWithFacebook(@Body tokenRequest: TokenRequest): Response<LoginResponse>

    @GET("api/travel-plans")
    suspend fun getAllTravelPlans(@Header("Authorization") token: String): Response<TravelPlanListResponse>

    @POST("api/travel-plans")
    @FormUrlEncoded
    suspend fun addTravelPlan(
        @Header("Authorization") token: String,
        @Field("name") name: String,
        @Field("estimation") estimation: String,
        @Field("totalDistance") totalDistance: String,
        @Field("startLocationName") startLocationName: String,
        @Field("startLocationPlaceId") startLocationPlaceId: String,
        @Field("startLocationAddress") startLocationAddress: String,
        @Field("startLocationLat") startLocationLat: Double,
        @Field("startLocationLng") startLocationLng: Double,
        @Field("startAt") startAt: String,
        @Field("endAt") endAt: String
    ): Response<TravelPlanResponse>

    @POST("/api/travel-plans/{id}")
    @Headers("X-HTTP-Method-Override: PUT")
    suspend fun updateTravelPlan(
        @Path("id") id: Int,
        @Header("Authorization") token: String,
        @Body travelPlanInput: TravelPlanInput
    ): Response<TravelPlanResponse>

    @DELETE("api/travel-plans/{id}")
    suspend fun deleteTravelPlan(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Response<TravelPlanResponse>

    @GET("api/travel-plans/{travelPlanId}/destinations/{destinationId}")
    suspend fun getDestinationById(
        @Header("Authorization") token: String,
        @Path("travelPlanId") travelPlanId: Int,
        @Path("destinationId") destinationId: Int
    ): Response<DestinationResponse>

    @GET("api/travel-plans/{travelPlanId}/destinations")
    suspend fun getAllDestinations(
        @Header("Authorization") authHeader: String,
        @Path("travelPlanId") travelPlanId: Int
    ): Response<DestinationListResponse>

    @FormUrlEncoded
    @POST("api/travel-plans/{travelPlanId}/destinations")
    suspend fun addDestination(
        @Header("Authorization") token: String,
        @Path("travelPlanId") travelPlanId: Int,
        @Field("startAt") startAt: String,
        @Field("endAt") endAt: String,
        @Field("vehicle") vehicle: String,
        @Field("note") note: String?,
        @Field("financialTransportation") financialTransportation: Double,
        @Field("financialLodging") financialLodging: Double,
        @Field("financialConsumption") financialConsumption: Double,
        @Field("financialEmergencyFund") financialEmergencyFund: Double,
        @Field("financialSouvenir") financialSouvenir: Double,
        @Field("locationName") locationName: String,
        @Field("locationPlaceId") locationPlaceId: String,
        @Field("locationAddress") locationAddress: String,
        @Field("locationLat") locationLat: Double,
        @Field("locationLng") locationLng: Double
    ): Response<DestinationResponse>

    @FormUrlEncoded
    @POST("api/travel-plans/{travelPlanId}/destinations/{destinationId}")
    suspend fun updateDestination(
        @Header("Authorization") token: String,
        @Path("travelPlanId") travelPlanId: Int,
        @Path("destinationId") destinationId: Int,
        @Field("startAt") startAt: String,
        @Field("endAt") endAt: String,
        @Field("vehicle") vehicle: String,
        @Field("note") note: String?,
        @Field("financialTransportation") financialTransportation: Double,
        @Field("financialLodging") financialLodging: Double,
        @Field("financialConsumption") financialConsumption: Double,
        @Field("financialEmergencyFund") financialEmergencyFund: Double,
        @Field("financialSouvenir") financialSouvenir: Double,
        @Field("locationName") locationName: String,
        @Field("locationPlaceId") locationPlaceId: String,
        @Field("locationAddress") locationAddress: String,
        @Field("locationLat") locationLat: Double,
        @Field("locationLng") locationLng: Double
    ): Response<DestinationResponse>

    @DELETE("api/travel-plans/{travelPlanId}/destinations/{destinationId}")
    suspend fun deleteDestination(
        @Header("Authorization") token: String,
        @Path("travelPlanId") travelPlanId: Int,
        @Path("destinationId") destinationId: Int
    ): Response<DestinationResponse>

    @GET("api/proxy/recomendation-places")
    suspend fun getRecommendations(
        @Header("Authorization") token: String,
        @Query("locationLat") locationLat: String,
        @Query("locationLng") locationLng: String
    ): Response<RecommendationsResponse>

}
