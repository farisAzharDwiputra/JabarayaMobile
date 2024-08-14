package com.example.projectcheva

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsBus
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Flight
import androidx.compose.material.icons.filled.Train
import androidx.compose.material.icons.filled.TwoWheeler
import androidx.compose.ui.graphics.vector.ImageVector
import com.google.android.gms.maps.model.LatLng
import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)

data class RegisterRequest(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("password") val password: String,
    @SerializedName("password_confirmation") val passwordConfirmation: String
)

data class AuthResponse(
    @SerializedName("status") val status: Boolean,
    @SerializedName("token") var accessToken: String?,
    @SerializedName("statusCode") val statusCode: Int,
    @SerializedName("message") val message: String,
    @SerializedName("data") val data: AuthData
)

data class LoginResponse(
    val status: String,
    val message: String,
    val token: String?
)

data class TokenRequest(val accessToken: String)

data class AuthData(
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: UserProfile
)

// Main response class
class UserProfileResponse {
    var status = false
    var statusCode = 0
    var message: String? = null
    var data: UserData? = null
}

// Data class containing user and token information
class UserData {
    var token: String? = null
    var user: UserProfile? = null
    var social_auth: List<String>? = null
}

// Standalone UserProfile class
class UserProfile {
    var id = 0
    var name: String? = null
    var email: String? = null
    var email_verified_at: String? = null
    var role: String? = null
    var avatar: String? = null
    var phone: String? = null
    var created_at: String? = null
    var updated_at: String? = null
}

data class UpdateUserProfileRequest(
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("phone") val phone: String,
    @SerializedName("avatar_url") val avatarUrl: String?
)

data class ChangePasswordRequest(
    @SerializedName("current_password") val currentPassword: String,
    @SerializedName("new_password") val newPassword: String,
    @SerializedName("new_password_confirmation") val newPasswordConfirmation: String
)

// Sample data for articles
data class Article(
    val title: String,
    val author: String,
    val date: String,
    val category: String,
    val imageResId: Int
)

val articles = listOf(
    Article(
        title = "Warisan Budaya Tak Benda Kota Bandung Tingkat Provinsi dan Nasional 2018-2023",
        author = "Muhammad Nur Shodiq",
        date = "20/05/2024",
        category = "Kuliner",
        imageResId = R.drawable.berita_terkini
    )
    // Add more items as needed
)

// Sample data for events
data class Event(
    val title: String,
    val date: String,
    val author: String,
    val location: String,
    val imageResId: Int
)

val events = listOf(
    Event(
        title = "Festival Musik Bandung 2024",
        date = "15/08/2024",
        author = "Muhammad Nur Shodiq",
        location = "Lapangan Gasibu",
        imageResId = R.drawable.berita_terkini
    )
    // Add more items as needed
)

// Sample data for news
data class NewsItem(
    val title: String,
    val author: String,
    val date: String,
    val category: String,
    val imageResId: Int
)

val newsItems = listOf(
    NewsItem(
        title = "Warisan Budaya Tak Benda Kota Bandung Tingkat Provinsi dan Nasional 2018-2023",
        author = "Muhammad Nur Shodiq",
        date = "20/05/2024",
        category = "Kuliner",
        imageResId = R.drawable.berita_terkini
    )
    // Add more items as needed
)

data class PlannedVisit(
    val name: String,
    val latitude: Double,
    val longitude: Double
) {
    fun toLatLng() = LatLng(latitude, longitude)
}

data class PlaceAutocompleteResponse(
    val predictions: List<Prediction>
)

data class PlacesResponse(
    val predictions: List<Prediction>
)

data class Prediction(
    val description: String,
    val place_id: String
)

data class GoogleLoginRequest(
    val id_token: String
)

enum class SignInMethod {
    Manual,
    Google,
    Facebook
}

enum class PasswordFieldType {
    CURRENT, NEW, CONFIRM
}

data class TravelPlan(
    val id: Int,
    val name: String,
    val estimation: String,
    val totalDistance: String,
    val startAt: String,
    val endAt: String,
    val startLocationId: Int,
    val userId: Int,
    val createdAt: String,
    val updatedAt: String,
    val startLocationLat: Double?,
    val startLocationLng: Double?
)


data class TravelPlanResponse(
    val status: Boolean,
    val statusCode: Int,
    val message: String,
    val data: TravelPlan
)

data class TravelPlanListResponse(
    val status: Boolean,
    val statusCode: Int,
    val message: String,
    val data: List<TravelPlan>
)

data class TravelPlanInput(
    val name: String,
    val estimation: String,
    val totalDistance: String,
    val startLocationName: String,
    val startLocationPlaceId: String,
    val startLocationAddress: String,
    val startLocationLat: Double,
    val startLocationLng: Double,
    val startAt: String,
    val endAt: String
)

sealed class TravelMethod(val apiValue: String, val displayName: String, val icon: ImageVector) {
    data object Car : TravelMethod("car", "Mobil", Icons.Filled.DirectionsCar)
    data object Bus : TravelMethod("bus", "Bus", Icons.Filled.DirectionsBus)
    data object Train : TravelMethod("train", "Kereta", Icons.Filled.Train)
    data object Motorcycle : TravelMethod("motorcycle", "Motor", Icons.Filled.TwoWheeler)
    data object Plane : TravelMethod("plane", "Pesawat", Icons.Filled.Flight)
}

// Data class for FinancialRecord
data class FinancialRecord(
    val id: Int,
    val transportation: Double,
    val lodging: Double,
    val consumption: Double,
    val emergencyFund: Double,
    val souvenir: Double,
    val total: Double,
    @SerializedName("destination_id") val destinationId: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

// Data class for DetailLocation
data class DetailLocation(
    val id: Int,
    @SerializedName("place_id") val placeId: String,
    val name: String,
    val lat: Double,
    val lng: Double,
    val address: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String
)

// Data class for the Destination
data class Destination(
    val id: Int,
    @SerializedName("startAt") val startAt: String,
    @SerializedName("endAt") val endAt: String,
    val note: String?,
    val vehicle: String,
    @SerializedName("travel_plan_id") val travelPlanId: Int,
    @SerializedName("detail_location_id") val detailLocationId: Int,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    @SerializedName("financial_record") val financialRecord: FinancialRecord,
    @SerializedName("detail_location") val detailLocation: DetailLocation
)

// Data class for the API response
data class DestinationResponse(
    val status: Boolean,
    @SerializedName("statusCode") val statusCode: Int,
    val message: String,
    val data: Destination
)

data class DestinationListResponse(
    val status: Boolean,
    @SerializedName("statusCode") val statusCode: Int,
    val message: String,
    val data: List<Destination>
)

data class DestinationInput(
    val travelPlanId: Int,
    val startAt: String,
    val endAt: String,
    val vehicle: String,
    val note: String?,
    val financialTransportation: Double,
    val financialLodging: Double,
    val financialConsumption: Double,
    val financialEmergencyFund: Double,
    val financialSouvenir: Double,
    val locationName: String,
    val locationPlaceId: String,
    val locationAddress: String,
    val locationLat: Double,
    val locationLng: Double
)

data class RecommendationsResponse(
    val status: Boolean,
    val statusCode: Int,
    val message: String,
    val data: List<Recommendation>
)

data class Recommendation(
    val place_id: String,
    val name: String,
    val lat: Double,
    val lng: Double,
    val rating: Double,
    val address: String,
    val phone: String,
    val website: String?,
    val opening_hours: List<String>
)