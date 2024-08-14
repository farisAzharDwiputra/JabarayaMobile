package com.example.projectcheva.fiturUtama

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.projectcheva.Destination
import com.example.projectcheva.DestinationInput
import com.example.projectcheva.Screens
import com.example.projectcheva.TravelMethod
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
    val authToken = getAuthToken(context)
    val hasToken = authToken != null
    val viewModel: TravelPlanViewModel = viewModel(factory = remember { TravelPlanViewModelFactory(context) })
    val travelPlans by viewModel.travelPlans.collectAsState()
    val destinations by viewModel.destinations.collectAsState()
    val deleteTravelPlanResponse by viewModel.deleteTravelPlanResponse.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    var selectedTravelPlanId by remember { mutableStateOf<Int?>(null) }
    var showUpdateDialog by remember { mutableStateOf(false) }
    var showDeleteConfirmationDialog by remember { mutableStateOf(false) }
    var showAddDialog by remember { mutableStateOf(false) }
    var showAddDestinationDialog by remember { mutableStateOf(false) }
    var selectedTravelPlan by remember { mutableStateOf<TravelPlan?>(null) }
    var refreshKey by remember { mutableStateOf(Unit) }
    var isSubmitting by remember { mutableStateOf(false) }
    var showDestinations by remember { mutableStateOf(false) }

    LaunchedEffect(refreshKey) {
        if (hasToken) {
            viewModel.fetchAllTravelPlans()
        } else {
            navController.navigate(Screens.NotLoggedPage.route) {
                popUpTo(navController.graph.id) { inclusive = true }
            }
        }
    }

    LaunchedEffect(selectedTravelPlanId) {
        selectedTravelPlanId?.let {
            viewModel.fetchAllDestinations(it)
        }
    }

    LaunchedEffect(deleteTravelPlanResponse) {
        deleteTravelPlanResponse?.let {
            if (it.status) {
                Toast.makeText(context, "Travel plan deleted successfully", Toast.LENGTH_SHORT).show()
                refreshKey = Unit
            } else {
                Toast.makeText(context, "Failed to delete travel plan", Toast.LENGTH_SHORT).show()
            }
            viewModel.clearDeleteTravelPlanResponse()
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Travel Plans") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { showAddDialog = true },
                containerColor = MaterialTheme.colorScheme.secondary
            ) {
                Icon(Icons.Filled.Add, contentDescription = "Add Travel Plan")
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        CircularProgressIndicator()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text("Loading...", style = MaterialTheme.typography.bodySmall)
                    }
                }
            } else {
                Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
                    if (hasToken) {
                        LazyColumn {
                            items(travelPlans) { travelPlan ->
                                TravelPlanItem(
                                    travelPlan = travelPlan,
                                    onClick = {
                                        selectedTravelPlanId = travelPlan.id
                                        selectedTravelPlan = travelPlan
                                        showDestinations = true
                                    },
                                    onDeleteClick = {
                                        selectedTravelPlanId = travelPlan.id
                                        selectedTravelPlan = travelPlan
                                        showDeleteConfirmationDialog = true
                                    },
                                    onMapClick = {
                                        navController.navigate(Screens.recomend.route)
                                    }
                                )
                            }
                        }

                        if (showDestinations && selectedTravelPlan != null) {
                            Spacer(modifier = Modifier.height(16.dp))
                            Text("Destinations", style = MaterialTheme.typography.titleMedium)

                            LazyColumn {
                                items(destinations) { destination ->
                                    DestinationItem(
                                        destination = destination,
                                        onClick = {
                                            // Handle destination item click if needed
                                        },
                                        onDeleteClick = {
                                            // Handle delete destination
                                        }
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(16.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                FloatingActionButton(
                                    onClick = { showAddDestinationDialog = true },
                                    modifier = Modifier.weight(1f),
                                    containerColor = MaterialTheme.colorScheme.secondary
                                ) {
                                    Icon(Icons.Filled.Add, contentDescription = "Add Destination")
                                }

                                Button(
                                    onClick = { navController.navigate(Screens.recomend.route) },
                                    modifier = Modifier.weight(2f)
                                ) {
                                    Text("Show Recommended Places")
                                }
                            }
                        }
                    } else {
                        Text("Please log in to access your travel plans.", style = MaterialTheme.typography.bodyLarge)
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
                                refreshKey = Unit
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

                    if (showAddDestinationDialog && selectedTravelPlan != null) {
                        AddDestinationDialog(
                            travelPlanId = selectedTravelPlan!!.id,
                            onDismiss = { showAddDestinationDialog = false },
                            onSubmit = { destinationInput ->
                                isSubmitting = true
                                viewModel.addDestination(destinationInput)
                                showAddDestinationDialog = false
                                refreshKey = Unit
                                isSubmitting = false
                            },
                            onSubmitSuccess = {
                                Toast.makeText(context, "Destination successfully added", Toast.LENGTH_SHORT).show()
                                refreshKey = Unit
                            },
                            isSubmitting = isSubmitting
                        )
                    }
                }
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
    var errors by remember { mutableStateOf<List<String>>(emptyList()) }

    AlertDialog(
        onDismissRequest = {
            if (!isSubmitting) onDismiss()
        },
        title = { Text("Add New Travel Plan") },
        text = {
            Column {
                // Input fields
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    enabled = !isSubmitting,
                    isError = errors.contains("Name is required.")
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = estimation,
                    onValueChange = { estimation = it },
                    label = { Text("Estimation") },
                    enabled = !isSubmitting,
                    isError = errors.contains("Estimation is required.")
                )
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    value = totalDistance,
                    onValueChange = { totalDistance = it },
                    label = { Text("Total Distance") },
                    enabled = !isSubmitting,
                    isError = errors.contains("Total Distance is required.")
                )
                Spacer(modifier = Modifier.height(8.dp))

                // Location Picker
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
                Spacer(modifier = Modifier.height(8.dp))

                // Date Pickers
                DatePickerWithIcon(
                    label = "Start Date",
                    selectedDate = startAt,
                    onDateSelected = { date -> startAt = date },
                    enabled = !isSubmitting
                )
                Spacer(modifier = Modifier.height(8.dp))
                DatePickerWithIcon(
                    label = "End Date",
                    selectedDate = endAt,
                    onDateSelected = { date -> endAt = date },
                    enabled = !isSubmitting
                )

                // Display validation errors
                errors.forEach { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newErrors = mutableListOf<String>()
                    if (name.isEmpty()) newErrors.add("Name is required.")
                    if (estimation.isEmpty()) newErrors.add("Estimation is required.")
                    if (totalDistance.isEmpty()) newErrors.add("Total Distance is required.")

                    if (newErrors.isEmpty()) {
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
                        focusManager.clearFocus()
                        onSubmit(newTravelPlan)
                        onSubmitSuccess()
                    } else {
                        errors = newErrors
                    }
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
            OutlinedButton(onClick = onDismiss, enabled = !isSubmitting) {
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

    Column(modifier = Modifier.fillMaxWidth()) {
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
                                    query = place.name ?: ""
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
        title = {
            Text(
                text = "Delete Travel Plan",
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        text = {
            Text(
                text = "Are you sure you want to delete the travel plan '${travelPlan.name}'?",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        },
        confirmButton = {
            Button(
                onClick = onConfirm,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.error
                )
            ) {
                Text(
                    text = "Delete",
                    color = MaterialTheme.colorScheme.onError
                )
            }
        },
        dismissButton = {
            Button(
                onClick = onDismiss,
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            ) {
                Text(
                    text = "Cancel",
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    )
}


@Composable
fun TravelPlanItem(
    travelPlan: TravelPlan,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit,
    onMapClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(onClick = onClick),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = travelPlan.name,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Estimated: ${travelPlan.estimation}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Total Distance: ${travelPlan.totalDistance} km",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "Start: ${travelPlan.startAt}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = "End: ${travelPlan.endAt}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                    // Displaying location details
                    if (travelPlan.startLocationLat != null && travelPlan.startLocationLng != null) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Location: Lat ${travelPlan.startLocationLat}, Lng ${travelPlan.startLocationLng}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }

                IconButton(
                    onClick = onDeleteClick,
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Delete,
                        contentDescription = "Delete Travel Plan",
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            }

            // Button to open the map view
            if (travelPlan.startLocationLat != null && travelPlan.startLocationLng != null) {
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = onMapClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = "Show Recommended Places",
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDestinationDialog(
    travelPlanId: Int,
    onDismiss: () -> Unit,
    onSubmit: (DestinationInput) -> Unit,
    onSubmitSuccess: () -> Unit,
    isSubmitting: Boolean
) {
    val context = LocalContext.current
    val placesClient = remember { Places.createClient(context) }

    var name by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var lat by remember { mutableStateOf(0.0) }
    var lng by remember { mutableStateOf(0.0) }
    var startAt by remember { mutableStateOf("") }
    var endAt by remember { mutableStateOf("") }
    var vehicle by remember { mutableStateOf<TravelMethod?>(null) }
    var note by remember { mutableStateOf("") }
    var financialTransportation by remember { mutableStateOf("") }
    var financialLodging by remember { mutableStateOf("") }
    var financialConsumption by remember { mutableStateOf("") }
    var financialEmergencyFund by remember { mutableStateOf("") }
    var financialSouvenir by remember { mutableStateOf("") }
    var placeId by remember { mutableStateOf("") }

    val focusManager = LocalFocusManager.current

    AlertDialog(
        onDismissRequest = {
            if (!isSubmitting) onDismiss()
        },
        title = { Text("Add New Destination") },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                TextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Name") },
                    enabled = !isSubmitting,
                    modifier = Modifier.fillMaxWidth()
                )
                TextField(
                    value = address,
                    onValueChange = { address = it },
                    label = { Text("Address") },
                    enabled = !isSubmitting,
                    modifier = Modifier.fillMaxWidth()
                )
                LocationPicker(
                    placesClient = placesClient,
                    onPlaceSelected = { place ->
                        name = place.name ?: ""
                        address = place.address ?: ""
                        place.latLng?.let {
                            lat = it.latitude
                            lng = it.longitude
                        }
                        placeId = place.id ?: ""
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

                var expanded by remember { mutableStateOf(false) }
                val vehicleOptions = listOf(
                    TravelMethod.Car,
                    TravelMethod.Bus,
                    TravelMethod.Train,
                    TravelMethod.Motorcycle,
                    TravelMethod.Plane
                )

                ExposedDropdownMenuBox(
                    expanded = expanded,
                    onExpandedChange = { expanded = !expanded }
                ) {
                    TextField(
                        value = vehicle?.displayName ?: "Select Vehicle",
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Vehicle") },
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Filled.ArrowDropDown,
                                contentDescription = null
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        vehicleOptions.forEach { travelMethod ->
                            DropdownMenuItem(
                                onClick = {
                                    vehicle = travelMethod
                                    expanded = false
                                },
                                leadingIcon = {
                                    Icon(
                                        imageVector = travelMethod.icon,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                text = {
                                    Text(text = travelMethod.displayName)
                                }
                            )
                        }
                    }
                }

                // Financial Inputs Table
                Text(
                    text = "Financial Details",
                    style = MaterialTheme.typography.titleSmall,
                    modifier = Modifier.padding(vertical = 8.dp)
                )
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FinancialTableRow(
                        label = "Transportation",
                        value = financialTransportation,
                        onValueChange = { financialTransportation = it }
                    )
                    FinancialTableRow(
                        label = "Lodging",
                        value = financialLodging,
                        onValueChange = { financialLodging = it }
                    )
                    FinancialTableRow(
                        label = "Consumption",
                        value = financialConsumption,
                        onValueChange = { financialConsumption = it }
                    )
                    FinancialTableRow(
                        label = "Emergency Fund",
                        value = financialEmergencyFund,
                        onValueChange = { financialEmergencyFund = it }
                    )
                    FinancialTableRow(
                        label = "Souvenir",
                        value = financialSouvenir,
                        onValueChange = { financialSouvenir = it }
                    )
                }

                TextField(
                    value = note,
                    onValueChange = { note = it },
                    label = { Text("Note") },
                    enabled = !isSubmitting,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val destinationInput = DestinationInput(
                        travelPlanId = travelPlanId,
                        startAt = startAt,
                        endAt = endAt,
                        vehicle = vehicle?.apiValue ?: "",
                        note = note,
                        financialTransportation = financialTransportation.toDoubleOrNull() ?: 0.0,
                        financialLodging = financialLodging.toDoubleOrNull() ?: 0.0,
                        financialConsumption = financialConsumption.toDoubleOrNull() ?: 0.0,
                        financialEmergencyFund = financialEmergencyFund.toDoubleOrNull() ?: 0.0,
                        financialSouvenir = financialSouvenir.toDoubleOrNull() ?: 0.0,
                        locationName = name,
                        locationPlaceId = placeId,
                        locationAddress = address,
                        locationLat = lat,
                        locationLng = lng
                    )
                    focusManager.clearFocus()
                    onSubmit(destinationInput)
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

@Composable
fun FinancialTableRow(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium
        )
        TextField(
            value = value,
            onValueChange = onValueChange,
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.widthIn(100.dp)
        )
    }
}

@Composable
fun DestinationItem(
    destination: Destination,
    onClick: () -> Unit,
    onDeleteClick: () -> Unit
) {
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
                    Text(
                        text = destination.detailLocation.name,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Text(
                        text = destination.detailLocation.address,
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Dates: ${destination.startAt} to ${destination.endAt}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Text(
                        text = "Vehicle: ${destination.vehicle}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    destination.note?.let {
                        Text(
                            text = "Note: $it",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
                IconButton(onClick = onDeleteClick) {
                    Icon(Icons.Default.Delete, contentDescription = "Delete Destination")
                }
            }
        }
    }
}