package com.example.projectcheva.fiturUtama

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectcheva.Destination
import com.example.projectcheva.DestinationInput
import com.example.projectcheva.DestinationResponse
import com.example.projectcheva.Recommendation
import com.example.projectcheva.TravelPlan
import com.example.projectcheva.TravelPlanInput
import com.example.projectcheva.TravelPlanResponse
import com.example.projectcheva.retrofit.AuthTokenManager
import com.example.projectcheva.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TravelPlanViewModel(private val context: Context) : ViewModel() {

    // Travel Plans
    private val _travelPlans = MutableStateFlow<List<TravelPlan>>(emptyList())
    val travelPlans: StateFlow<List<TravelPlan>> = _travelPlans

    private val _addTravelPlanResponse = MutableStateFlow<TravelPlanResponse?>(null)
    private val _updateTravelPlanResponse = MutableStateFlow<TravelPlanResponse?>(null)
    private val _deleteTravelPlanResponse = MutableStateFlow<TravelPlanResponse?>(null)
    val deleteTravelPlanResponse: StateFlow<TravelPlanResponse?> = _deleteTravelPlanResponse

    // Destinations
    private val _destinations = MutableStateFlow<List<Destination>>(emptyList())
    val destinations: StateFlow<List<Destination>> = _destinations

    private val _addDestinationResponse = MutableStateFlow<DestinationResponse?>(null)
    val addDestinationResponse: StateFlow<DestinationResponse?> = _addDestinationResponse

    private val _updateDestinationResponse = MutableStateFlow<DestinationResponse?>(null)
    val updateDestinationResponse: StateFlow<DestinationResponse?> = _updateDestinationResponse

    private val _deleteDestinationResponse = MutableStateFlow<DestinationResponse?>(null)
    val deleteDestinationResponse: StateFlow<DestinationResponse?> = _deleteDestinationResponse

    private val _recommendations = MutableStateFlow<List<Recommendation>>(emptyList())
    val recommendations: StateFlow<List<Recommendation>> = _recommendations

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private fun getAuthToken(): String? {
        return AuthTokenManager.getAuthToken(context)
    }

    // Fetch all travel plans
    fun fetchAllTravelPlans() {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.getAllTravelPlans("Bearer $token")
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.status) {
                        _travelPlans.value = responseBody.data
                    } else {
                        _travelPlans.value = emptyList()
                    }
                } else {
                    _travelPlans.value = emptyList()
                }
            } catch (e: Exception) {
                _travelPlans.value = emptyList()
            }
        }
    }

    // Add a travel plan
    fun addTravelPlan(travelPlan: TravelPlanInput) {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.addTravelPlan(
                    token = "Bearer $token",
                    name = travelPlan.name,
                    estimation = travelPlan.estimation,
                    totalDistance = travelPlan.totalDistance,
                    startLocationName = travelPlan.startLocationName,
                    startLocationPlaceId = travelPlan.startLocationPlaceId,
                    startLocationAddress = travelPlan.startLocationAddress,
                    startLocationLat = travelPlan.startLocationLat,
                    startLocationLng = travelPlan.startLocationLng,
                    startAt = travelPlan.startAt,
                    endAt = travelPlan.endAt
                )
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody is TravelPlanResponse) {
                        _addTravelPlanResponse.value = responseBody
                        fetchAllTravelPlans()
                    } else {
                        _addTravelPlanResponse.value = null
                    }
                } else {
                    _addTravelPlanResponse.value = null
                }
            } catch (e: Exception) {
                _addTravelPlanResponse.value = null
            }
        }
    }

    // Update a travel plan
    fun updateTravelPlan(id: Int, travelPlanInput: TravelPlanInput) {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.updateTravelPlan(id, token, travelPlanInput)
                if (response.isSuccessful) {
                    _updateTravelPlanResponse.value = response.body()
                    fetchAllTravelPlans()
                } else {
                    // Handle API error
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }

    // Delete a travel plan
    fun deleteTravelPlan(id: Int) {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.deleteTravelPlan(id, "Bearer $token")
                if (response.isSuccessful) {
                    _deleteTravelPlanResponse.value = response.body()
                    fetchAllTravelPlans()
                } else {
                    // Handle API error
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }

    // Fetch all destinations for a specific travel plan
    fun fetchAllDestinations(travelPlanId: Int) {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.getAllDestinations("Bearer $token", travelPlanId)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.status) {
                        _destinations.value = responseBody.data
                    } else {
                        _destinations.value = emptyList()
                    }
                } else {
                    _destinations.value = emptyList()
                }
            } catch (e: Exception) {
                _destinations.value = emptyList()
            }
        }
    }

    fun addDestination(destinationInput: DestinationInput) {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.addDestination(
                    token = "Bearer $token",
                    travelPlanId = destinationInput.travelPlanId,
                    startAt = destinationInput.startAt,
                    endAt = destinationInput.endAt,
                    vehicle = destinationInput.vehicle,
                    note = destinationInput.note,
                    financialTransportation = destinationInput.financialTransportation,
                    financialLodging = destinationInput.financialLodging,
                    financialConsumption = destinationInput.financialConsumption,
                    financialEmergencyFund = destinationInput.financialEmergencyFund,
                    financialSouvenir = destinationInput.financialSouvenir,
                    locationName = destinationInput.locationName,
                    locationPlaceId = destinationInput.locationPlaceId,
                    locationAddress = destinationInput.locationAddress,
                    locationLat = destinationInput.locationLat,
                    locationLng = destinationInput.locationLng
                )
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody is DestinationResponse) {
                        _addDestinationResponse.value = responseBody
                        fetchAllDestinations(destinationInput.travelPlanId) // Refresh data after adding
                    } else {
                        _addDestinationResponse.value = null
                    }
                } else {
                    _addDestinationResponse.value = null
                }
            } catch (e: Exception) {
                _addDestinationResponse.value = null
            }
        }
    }

    // Update an existing destination
    fun updateDestination(
        travelPlanId: Int,
        destinationId: Int,
        startAt: String,
        endAt: String,
        vehicle: String,
        note: String?,
        financialTransportation: Double,
        financialLodging: Double,
        financialConsumption: Double,
        financialEmergencyFund: Double,
        financialSouvenir: Double,
        locationName: String,
        locationPlaceId: String,
        locationAddress: String,
        locationLat: Double,
        locationLng: Double
    ) {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.updateDestination(
                    token = "Bearer $token",
                    travelPlanId = travelPlanId,
                    destinationId = destinationId,
                    startAt = startAt,
                    endAt = endAt,
                    vehicle = vehicle,
                    note = note,
                    financialTransportation = financialTransportation,
                    financialLodging = financialLodging,
                    financialConsumption = financialConsumption,
                    financialEmergencyFund = financialEmergencyFund,
                    financialSouvenir = financialSouvenir,
                    locationName = locationName,
                    locationPlaceId = locationPlaceId,
                    locationAddress = locationAddress,
                    locationLat = locationLat,
                    locationLng = locationLng
                )
                if (response.isSuccessful) {
                    _updateDestinationResponse.value = response.body()
                    fetchAllDestinations(travelPlanId) // Refresh data after updating
                } else {
                    // Handle API error
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }

    // Delete a destination
    fun deleteDestination(travelPlanId: Int, destinationId: Int) {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.deleteDestination(
                    token = "Bearer $token",
                    travelPlanId = travelPlanId,
                    destinationId = destinationId
                )
                if (response.isSuccessful) {
                    _deleteDestinationResponse.value = response.body()
                    fetchAllDestinations(travelPlanId) // Refresh data after deleting
                } else {
                    // Handle API error
                }
            } catch (e: Exception) {
                // Handle network or other errors
            }
        }
    }

    // Clear response states
    fun clearAddTravelPlanResponse() {
        _addTravelPlanResponse.value = null
    }

    fun clearUpdateTravelPlanResponse() {
        _updateTravelPlanResponse.value = null
    }

    fun clearDeleteTravelPlanResponse() {
        _deleteTravelPlanResponse.value = null
    }

    fun clearAddDestinationResponse() {
        _addDestinationResponse.value = null
    }

    fun clearUpdateDestinationResponse() {
        _updateDestinationResponse.value = null
    }

    fun clearDeleteDestinationResponse() {
        _deleteDestinationResponse.value = null
    }

    fun fetchRecommendations(locationLat: String, locationLng: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.getRecommendations(
                    token = "Bearer $token",
                    locationLat = locationLat,
                    locationLng = locationLng
                )
                if (response.isSuccessful) {
                    _recommendations.value = response.body()?.data ?: emptyList()
                } else {
                    _errorMessage.value = "Error: ${response.message()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Exception: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

}

