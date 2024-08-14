package com.example.projectcheva.fiturUtama

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectcheva.DestinationResponse
import com.example.projectcheva.TravelPlan
import com.example.projectcheva.TravelPlanInput
import com.example.projectcheva.TravelPlanResponse
import com.example.projectcheva.UpdateDestinationResponse
import com.example.projectcheva.retrofit.AuthTokenManager
import com.example.projectcheva.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TravelPlanViewModel(private val context: Context) : ViewModel() {

    private val _travelPlans = MutableStateFlow<List<TravelPlan>>(emptyList())
    val travelPlans: StateFlow<List<TravelPlan>> = _travelPlans

    private val _addTravelPlanResponse = MutableStateFlow<TravelPlanResponse?>(null)
    val addTravelPlanResponse: StateFlow<TravelPlanResponse?> = _addTravelPlanResponse

    private val _updateTravelPlanResponse = MutableStateFlow<TravelPlanResponse?>(null)
    val updateTravelPlanResponse: StateFlow<TravelPlanResponse?> = _updateTravelPlanResponse

    private val _deleteTravelPlanResponse = MutableStateFlow<TravelPlanResponse?>(null)
    val deleteTravelPlanResponse: StateFlow<TravelPlanResponse?> = _deleteTravelPlanResponse

    private val _destinationResponse = MutableStateFlow<DestinationResponse?>(null)
    val destinationResponse: StateFlow<DestinationResponse?> = _destinationResponse

    private val _addDestinationResponse = MutableStateFlow<DestinationResponse?>(null)
    val addDestinationResponse: StateFlow<DestinationResponse?> = _addDestinationResponse

    private val _updateDestinationResponse = MutableStateFlow<UpdateDestinationResponse?>(null)
    val updateDestinationResponse: StateFlow<UpdateDestinationResponse?> = _updateDestinationResponse

    private fun getAuthToken(): String? {
        return AuthTokenManager.getAuthToken(context)
    }

    fun fetchAllTravelPlans() {
        viewModelScope.launch {
            try {
                Log.d("TravelPlanList", "Fetching all travel plans")
                val token = getAuthToken() ?: run {
                    Log.d("TravelPlanList", "Token is null")
                    return@launch
                }
                val response = RetrofitInstance.api.getAllTravelPlans("Bearer $token")

                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null && responseBody.status) {
                        _travelPlans.value = responseBody.data
                        Log.d("TravelPlanList", "Travel plans updated: ${_travelPlans.value}")
                    } else {
                        Log.e("TravelPlanList", "Error: ${responseBody?.message}")
                        _travelPlans.value = emptyList()
                    }
                } else {
                    Log.e("TravelPlanList", "Error fetching travel plans: ${response.code()} - ${response.message()}")
                    Log.e("TravelPlanList", "Error body: ${response.errorBody()?.string()}")
                    _travelPlans.value = emptyList()
                }
            } catch (e: Exception) {
                Log.e("TravelPlanList", "Error fetching travel plans", e)
                _travelPlans.value = emptyList()
            }
        }
    }

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
                        Log.d("AddTravelPlan", "Success: $responseBody")
                    } else {
                        Log.e("AddTravelPlan", "Unexpected response body: $responseBody")
                        _addTravelPlanResponse.value = null
                    }
                } else {
                    Log.e("AddTravelPlan", "Error: ${response.code()} - ${response.message()}")
                    Log.e("AddTravelPlan", "Error body: ${response.errorBody()?.string()}")
                    _addTravelPlanResponse.value = null
                }
            } catch (e: Exception) {
                Log.e("AddTravelPlan", "Exception: ${e.message}")
                e.printStackTrace()
                _addTravelPlanResponse.value = null
            }
        }
    }

    fun updateTravelPlan(id: Int, travelPlanInput: TravelPlanInput) {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                val response = RetrofitInstance.api.updateTravelPlan(id, token, travelPlanInput)
                if (response.isSuccessful) {
                    _updateTravelPlanResponse.value = response.body()
                    fetchAllTravelPlans() // Refresh data after updating
                } else {
                    Log.e("UpdateTravelPlan", "Error: ${response.code()} - ${response.message()}")
                    // Handle API error
                }
            } catch (e: Exception) {
                Log.e("UpdateTravelPlan", "Exception: ${e.message}")
                e.printStackTrace()
                // Handle network or other errors
            }
        }
    }

    fun deleteTravelPlan(id: Int) {
        viewModelScope.launch {
            try {
                val token = getAuthToken() ?: return@launch
                Log.d("DeleteTravelPlan", "Token: Bearer $token")
                val response = RetrofitInstance.api.deleteTravelPlan(id, "Bearer $token")
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _deleteTravelPlanResponse.value = responseBody
                    fetchAllTravelPlans() // Refresh data after deleting
                } else {
                    Log.e("DeleteTravelPlan", "Error: ${response.code()} - ${response.message()}")
                    Log.e("DeleteTravelPlan", "Error body: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("DeleteTravelPlan", "Exception: ${e.message}")
                e.printStackTrace()
            }
        }
    }

    fun clearDeleteTravelPlanResponse() {
        _deleteTravelPlanResponse.value = null
    }
}
