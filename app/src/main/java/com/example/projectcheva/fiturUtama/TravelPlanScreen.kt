package com.example.projectcheva.fiturUtama

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projectcheva.Screens
import com.example.projectcheva.TravelPlan
import com.example.projectcheva.TravelPlanInput
import com.example.projectcheva.retrofit.AuthTokenManager.getAuthToken
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.AutocompletePrediction
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.api.net.FetchPlaceRequest
import com.google.android.libraries.places.api.net.FindAutocompletePredictionsRequest
import com.google.android.libraries.places.api.net.PlacesClient
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TravelPlanScreen(navController: NavController) {
    val context = LocalContext.current
    val viewModel: TravelPlanViewModel = viewModel(factory = remember { TravelPlanViewModelFactory(context) })
    val travelPlans by viewModel.travelPlans.collectAsState()
    val deleteTravelPlanResponse by viewModel.deleteTravelPlanResponse.collectAsState()
    var selectedTravelPlanId by remember { mutableStateOf<Int?>(null) }
    var showUpdateDialog by remember { mutableStateOf(false) }
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    var selectedTravelPlan by remember { mutableStateOf<TravelPlan?>(null) }
    var refreshKey by remember { mutableStateOf(Unit) }
    var isSubmitting by remember { mutableStateOf(false) }

    // Token management
    val authToken = getAuthToken(context)
    val hasToken = authToken != null

    // Fetch data when screen appears or refresh key changes, only if token is present
    LaunchedEffect(refreshKey) {
        if (hasToken) {
            viewModel.fetchAllTravelPlans()
        } else {
            // Navigate to NotLoggedPage and pop back stack
            navController.popBackStack()
            navController.navigate(Screens.NotLoggedPage.route)
        }
    }

    // Show success or failure message for deleting a travel plan
    LaunchedEffect(deleteTravelPlanResponse) {
        deleteTravelPlanResponse?.let {
            if (it.status) {
                Toast.makeText(context, "Travel plan deleted successfully", Toast.LENGTH_SHORT).show()
                refreshKey = Unit  // Trigger data refresh
            } else {
                Toast.makeText(context, "Failed to delete travel plan", Toast.LENGTH_SHORT).show()
            }
            viewModel.clearDeleteTravelPlanResponse()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Travel Plans") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { showAddDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Add Travel Plan")
            }
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            if (hasToken) {
                LazyColumn {
                    items(travelPlans) { travelPlan ->
                        TravelPlanItem(
                            travelPlan = travelPlan,
                            onClick = {
                                selectedTravelPlanId = travelPlan.id
                                selectedTravelPlan = travelPlan
                                showUpdateDialog = true
                            },
                            onDeleteClick = {
                                selectedTravelPlanId = travelPlan.id
                                selectedTravelPlan = travelPlan
                                showDeleteConfirmationDialog = true
                            }
                        )
                    }
                }
            } else {
                Text("Please log in to access your travel plans.")
            }

            if (showAddDialog) {
                AddTravelPlanDialog(
                    onDismiss = { showAddDialog = false },
                    onSubmit = { travelPlanInput ->
                        isSubmitting = true
                        viewModel.addTravelPlan(travelPlanInput)
                        showAddDialog = false
                        refreshKey = Unit
                        isSubmitting = false
                    },
                    onSubmitSuccess = {
                        Toast.makeText(context, "Travel plan successfully created", Toast.LENGTH_SHORT).show()
                        refreshKey = Unit
                    },
                    isSubmitting = isSubmitting
                )
            }

            if (showUpdateDialog && selectedTravelPlan != null) {
                UpdateTravelPlanDialog(
                    travelPlan = selectedTravelPlan!!,
                    onDismiss = { showUpdateDialog = false },
                    onSubmit = { travelPlanInput ->
                        isSubmitting = true
                        viewModel.updateTravelPlan(selectedTravelPlan!!.id, travelPlanInput)
                        showUpdateDialog = false
                        refreshKey = Unit  // Trigger data refresh
                        isSubmitting = false
                    },
                )
            }

            if (showDeleteConfirmationDialog && selectedTravelPlan != null) {
                DeleteConfirmationDialog(
                    travelPlan = selectedTravelPlan!!,
                    onDismiss = { showDeleteConfirmationDialog = false },
                    onConfirm = {
                        selectedTravelPlan?.let { plan ->
                            viewModel.deleteTravelPlan(plan.id)
                        }
                        showDeleteConfirmationDialog = false
                    }
                )
            }
        }
    }
}

@Composable
fun AddTravelPlanDialog(
    onDismiss: () -> Unit,
    onSubmit: (TravelPlanInput) -> Unit,
    onSubmitSuccess: () -> Unit,
    isSubmitting: Boolean
) {
    val context = LocalContext.current
    val placesClient = remember { Places.createClient(context) }

    val focusManager = LocalFocusManager.current

    var name by remember { mutableStateOf("") }
    var estimation by remember { mutableStateOf("") }
    var totalDistance by remember { mutableStateOf("") }
    var startLocationName by remember { mutableStateOf("") }
    var startLocationPlaceId by remember { mutableStateOf("") }
    var startLocationAddress by remember { mutableStateOf("") }
    var startLocationLat by remember { mutableStateOf(0.0) }
    var startLocationLng by remember { mutableStateOf(0.0) }
    var startAt by remember { mutableStateOf("") }
    var endAt by remember { mutableStateOf("") }

    AlertDialog(
        modifier = Modifier
            .pointerInput(Unit) {
                detectTapGestures(onTap = { focusManager.clearFocus() })
            },
        onDismissRequest = {
            if (!isSubmitting) onDismiss()
        },
        title = { Text("Add New Travel Plan") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    enabled = !isSubmitting
                )
                TextField(
                    value = estimation,
                    onValueChange = { estimation = it },
                    label = { Text("Estimation") },
                    enabled = !isSubmitting
                )
                TextField(
                    value = totalDistance,
                    onValueChange = { totalDistance = it },
                    label = { Text("Total Distance") },
                    enabled = !isSubmitting
                )

                LocationPicker(
                    placesClient = placesClient,
                    onPlaceSelected = { place ->
                        startLocationName = place.name ?: ""
                        startLocationPlaceId = place.id ?: ""
                        startLocationAddress = place.address ?: ""
                        place.latLng?.let {
                            startLocationLat = it.latitude
                            startLocationLng = it.longitude
                        }
                    },
                    enabled = !isSubmitting
                )

                DatePickerWithIcon(
                    label = "Start Date",
                    selectedDate = startAt,
                    onDateSelected = { date -> startAt = date },
                    enabled = !isSubmitting
                )

                DatePickerWithIcon(
                    label = "End Date",
                    selectedDate = endAt,
                    onDateSelected = { date -> endAt = date },
                    enabled = !isSubmitting
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newTravelPlan = TravelPlanInput(
                        name = name,
                        estimation = estimation,
                        totalDistance = totalDistance,
                        startLocationName = startLocationName,
                        startLocationPlaceId = startLocationPlaceId,
                        startLocationAddress = startLocationAddress,
                        startLocationLat = startLocationLat,
                        startLocationLng = startLocationLng,
                        startAt = startAt,
                        endAt = endAt
                    )
                    focusManager.clearFocus() // Optionally clear focus before submitting
                    onSubmit(newTravelPlan)
                    onSubmitSuccess()
                },
                enabled = !isSubmitting
            ) {
                if (isSubmitting) {
                    CircularProgressIndicator(modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Text("Submit")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss, enabled = !isSubmitting) {
                Text("Cancel")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithIcon(
    label: String,
    selectedDate: String,
    onDateSelected: (String) -> Unit,
    enabled: Boolean = true
) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    Calendar.getInstance()

    // Parse the selected date into milliseconds, if possible
    val initialDateMillis = try {
        dateFormat.parse(selectedDate)?.time
    } catch (e: Exception) {
        null
    }

    // Remember DatePickerState, setting the initial date if available
    val state = rememberDatePickerState(
        initialSelectedDateMillis = initialDateMillis
    )

    var showDatePickerDialog by remember { mutableStateOf(false) }

    // Show the DatePickerDialog when triggered
    if (showDatePickerDialog) {
        DatePickerDialog(
            onDismissRequest = { showDatePickerDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    state.selectedDateMillis?.let { millis ->
                        onDateSelected(dateFormat.format(Date(millis)))
                    }
                    showDatePickerDialog = false
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePickerDialog = false }) {
                    Text("Cancel")
                }
            },
            content = {
                DatePicker(
                    state = state,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        )
    }

    OutlinedTextField(
        value = selectedDate,
        onValueChange = { },
        label = { Text(label) },
        readOnly = true,
        enabled = enabled,
        modifier = Modifier
            .clickable(enabled) { showDatePickerDialog = true }
            .fillMaxWidth(),
        trailingIcon = {
            IconButton(
                onClick = { showDatePickerDialog = true },
                enabled = enabled
            ) {
                Icon(Icons.Default.CalendarToday, contentDescription = "Select Date")
            }
        }
    )
}

@Composable
fun LocationPicker(
    placesClient: PlacesClient,
    onPlaceSelected: (Place) -> Unit,
    enabled: Boolean = true
) {
    var query by remember { mutableStateOf("") }
    var predictions by remember { mutableStateOf<List<AutocompletePrediction>>(emptyList()) }
    var selectedPlace by remember { mutableStateOf<Place?>(null) }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(query) {
        if (enabled && query.isNotEmpty()) {
            isLoading = true
            errorMessage = null
            val request = FindAutocompletePredictionsRequest.builder()
                .setQuery(query)
                .build()

            placesClient.findAutocompletePredictions(request)
                .addOnSuccessListener { response ->
                    predictions = response.autocompletePredictions
                    isLoading = false
                }
                .addOnFailureListener { exception ->
                    isLoading = false
                    errorMessage = "Error fetching predictions: ${exception.localizedMessage}"
                }
        } else {
            predictions = emptyList()
        }
    }

    Column(modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = selectedPlace?.name ?: query,
            onValueChange = { newQuery ->
                selectedPlace = null
                query = newQuery
            },
            label = { Text("Search Location") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (query.isNotEmpty() || selectedPlace != null) {
                    IconButton(onClick = {
                        selectedPlace = null
                        query = ""
                    }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear Query")
                    }
                }
            },
            modifier = Modifier.fillMaxWidth(),
            enabled = enabled,
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
        } else if (errorMessage != null) {
            Text(
                text = errorMessage!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                textAlign = TextAlign.Center
            )
        } else if (enabled && selectedPlace == null) {
            LazyColumn(modifier = Modifier.fillMaxWidth()) {
                items(predictions) { prediction ->
                    val placeId = prediction.placeId
                    val placeName = prediction.getPrimaryText(null).toString()
                    val placeAddress = prediction.getSecondaryText(null).toString()

                    ListItem(
                        headlineContent = { Text(text = placeName) },
                        supportingContent = { Text(text = placeAddress) },
                        modifier = Modifier
                            .clickable {
                                fetchPlaceDetails(placesClient, placeId) { place ->
                                    selectedPlace = place
                                    query = place.name
                                        ?: "" // Update the query with the selected place's name
                                    onPlaceSelected(place)
                                }
                            }
                            .padding(8.dp)
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .clip(RoundedCornerShape(8.dp))
                            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline))
                    )
                }

                if (predictions.isEmpty() && query.isNotEmpty()) {
                    item {
                        Text(
                            text = "No results found",
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

private fun fetchPlaceDetails(
    placesClient: PlacesClient,
    placeId: String,
    onPlaceDetailsFetched: (Place) -> Unit
) {
    val request = FetchPlaceRequest.builder(placeId, listOf(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG)).build()

    placesClient.fetchPlace(request)
        .addOnSuccessListener { response ->
            val place = response.place
            onPlaceDetailsFetched(place)
        }
        .addOnFailureListener { exception ->
            // Handle the error
            Log.e("LocationPicker", "Place details could not be fetched: ${exception.message}")
        }
}

@Composable
fun UpdateTravelPlanDialog(
    travelPlan: TravelPlan,
    onDismiss: () -> Unit,
    onSubmit: (TravelPlanInput) -> Unit
) {
    var name by remember { mutableStateOf(travelPlan.name) }
    var estimation by remember { mutableStateOf(travelPlan.estimation) }
    var totalDistance by remember { mutableStateOf(travelPlan.totalDistance) }
    var startAt by remember { mutableStateOf(travelPlan.startAt) }
    var endAt by remember { mutableStateOf(travelPlan.endAt) }
    val startLocationName by remember { mutableStateOf("") }
    val startLocationPlaceId by remember { mutableStateOf("") }
    val startLocationAddress by remember { mutableStateOf("") }
    val startLocationLat by remember { mutableStateOf(0.0) }
    val startLocationLng by remember { mutableStateOf(0.0) }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Update Travel Plan") },
        text = {
            Column {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") }
                )
                TextField(
                    value = estimation,
                    onValueChange = { estimation = it },
                    label = { Text("Estimation") }
                )
                TextField(
                    value = totalDistance,
                    onValueChange = { totalDistance = it },
                    label = { Text("Total Distance") }
                )
                TextField(
                    value = startAt,
                    onValueChange = { startAt = it },
                    label = { Text("Start At") }
                )
                TextField(
                    value = endAt,
                    onValueChange = { endAt = it },
                    label = { Text("End At") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                onSubmit(
                    TravelPlanInput(
                        name = name,
                        estimation = estimation,
                        totalDistance = totalDistance,
                        startLocationName = startLocationName,
                        startLocationPlaceId = startLocationPlaceId,
                        startLocationAddress = startLocationAddress,
                        startLocationLat = startLocationLat,
                        startLocationLng = startLocationLng,
                        startAt = startAt,
                        endAt = endAt
                    )
                )
                onDismiss()
            }) {
                Text("Update")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun DeleteConfirmationDialog(
    travelPlan: TravelPlan,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Delete Travel Plan") },
        text = { Text("Are you sure you want to delete the travel plan '${travelPlan.name}'?") },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Delete")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun TravelPlanItem(
    travelPlan: TravelPlan,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
    Log.d("TravelPlanItem", "Rendering TravelPlanItem with name: ${travelPlan.name}")

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = travelPlan.name, style = MaterialTheme.typography.bodySmall)
                    Text(text = "Estimated: ${travelPlan.estimation}")
                    Text(text = "Total Distance: ${travelPlan.totalDistance} km")
                    Text(text = "Start: ${travelPlan.startAt}")
                    Text(text = "End: ${travelPlan.endAt}")
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Filled.Delete, contentDescription = "Delete Travel Plan")
                }
            }
        }
    }
}