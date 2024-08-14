package com.example.projectcheva.fiturUtama

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import kotlinx.coroutines.delay

@Composable
fun MapScreen(
    initialPlace: LatLng? = null, // Initial place marker
    destinationPlace: LatLng? = null // Destination place marker
) {
    val context = LocalContext.current
    var hasLocationPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        )
    }

    val locationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted ->
            hasLocationPermission = isGranted
        }
    )

    LaunchedEffect(Unit) {
        if (!hasLocationPermission) {
            locationPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }
    }

    if (hasLocationPermission) {
        val bandung = LatLng(-6.9175, 107.6191)
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(bandung, 12f)
        }

        var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
        var searchResults by remember { mutableStateOf(listOf<AutocompletePrediction>()) }
        val placesClient = Places.createClient(context)
        var selectedLocation by remember { mutableStateOf<LatLng?>(null) }
        var showSearchResults by remember { mutableStateOf(true) }

        // Show the user's current location
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        var userLocation by remember { mutableStateOf<LatLng?>(null) }

        LaunchedEffect(hasLocationPermission) {
            if (hasLocationPermission) {
                fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                    location?.let {
                        userLocation = LatLng(it.latitude, it.longitude)
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(userLocation!!, 12f)
                    }
                }
            }
        }

        Box(modifier = Modifier
            .fillMaxSize()
            .clickable(
                onClick = {
                    showSearchResults = false
                },
                indication = null,
                interactionSource = remember { MutableInteractionSource() }
            )
        ) {
            Column(modifier = Modifier.fillMaxSize()) {
                TextField(
                    value = searchQuery,
                    onValueChange = { newValue ->
                        searchQuery = newValue
                        showSearchResults = true
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clickable(
                            onClick = {
                                showSearchResults = true
                            },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                    label = { Text("Search for a destination") }
                )

                if (showSearchResults) {
                    LaunchedEffect(searchQuery.text) {
                        delay(300)  // debounce time in milliseconds
                        if (searchQuery.text.isNotEmpty()) {
                            val request = FindAutocompletePredictionsRequest.builder()
                                .setQuery(searchQuery.text)
                                .build()

                            placesClient.findAutocompletePredictions(request).addOnSuccessListener { response ->
                                searchResults = response.autocompletePredictions
                            }
                        } else {
                            searchResults = listOf()
                        }
                    }

                    LazyColumn {
                        items(searchResults) { prediction ->
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                Text(
                                    text = prediction.getFullText(null).toString(),
                                    modifier = Modifier
                                        .weight(1f)
                                        .clickable {
                                            val placeId = prediction.placeId
                                            val placeFields = listOf(Place.Field.LAT_LNG)
                                            val request = FetchPlaceRequest.builder(placeId, placeFields).build()

                                            placesClient.fetchPlace(request).addOnSuccessListener { response ->
                                                val place = response.place
                                                selectedLocation = place.latLng
                                                if (selectedLocation != null) {
                                                    cameraPositionState.position = CameraPosition.fromLatLngZoom(selectedLocation!!, 15f)
                                                }
                                                showSearchResults = false
                                            }
                                        }
                                )
                            }
                        }
                    }
                }

                GoogleMap(
                    modifier = Modifier.weight(1f),
                    cameraPositionState = cameraPositionState
                ) {
                    userLocation?.let {
                        Marker(
                            state = rememberMarkerState(position = it),
                            title = "Your Location",
                            snippet = "This is your current location"
                        )
                    }

                    initialPlace?.let {
                        Marker(
                            state = rememberMarkerState(position = it),
                            title = "Initial Place",
                            snippet = "Initial place marker"
                        )
                    }

                    destinationPlace?.let {
                        Marker(
                            state = rememberMarkerState(position = it),
                            title = "Destination Place",
                            snippet = "Destination place marker"
                        )
                    }

                    selectedLocation?.let {
                        Marker(
                            state = rememberMarkerState(position = it),
                            title = "Selected Location",
                            snippet = "Marker in selected location"
                        )
                    }
                }
            }
        }
    }
}