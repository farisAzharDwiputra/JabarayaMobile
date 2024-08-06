package com.example.projectcheva.screen

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.MutableLiveData
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.projectcheva.FontProvider
import com.example.projectcheva.Screens
import com.example.projectcheva.SignInMethod
import com.example.projectcheva.UserProfile
import com.example.projectcheva.retrofit.AuthTokenManager.getLoginMethod
import com.example.projectcheva.retrofit.AuthViewModel

@Composable
fun ProfileScreen(
    navController: NavController,
    viewModel: AuthViewModel,
    onLogout: () -> Unit,
    signInMethod: SignInMethod
) {
    val context = LocalContext.current
    val userProfile by viewModel.userProfile.observeAsState()
    val isUpdating by remember { mutableStateOf(false) }
    var isLoading by remember { mutableStateOf(false) }

    // Fetch user profile data based on the sign-in method
    LaunchedEffect(signInMethod) {
        viewModel.fetchUserProfile(signInMethod)
    }

    val loginMethod = getLoginMethod(context)
    Log.d("loginMethod", loginMethod.toString())

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {

                // Header
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Profil",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    text = "Profil Anda",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(16.dp))

                // Profile Container
                userProfile?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(5.dp))
                            .background(Color(0xFF68B1FF)) // Fix color usage
                            .padding(8.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(
                                painter = rememberAsyncImagePainter(it.avatar),
                                contentDescription = "User Avatar",
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape)
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            Column {
                                Text(
                                    text = it.name ?: "Nama",
                                    fontFamily = FontProvider.urbanist,
                                    fontWeight = FontWeight.ExtraBold
                                )
                                Text(
                                    modifier = Modifier.padding(top = 16.dp),
                                    text = "${it.phone} • ${it.email}",
                                    fontFamily = FontProvider.urbanist,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        OutlinedButton(
                            onClick = { navController.navigate(Screens.EditProfile.route) },
                            border = BorderStroke(1.dp, Color.Black),
                            modifier = Modifier
                                .align(Alignment.End)
                                .fillMaxWidth(0.55f),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White,
                                contentColor = Color.Black
                            )
                        ) {
                            Icon(
                                modifier = Modifier.size(16.dp),
                                imageVector = Icons.Filled.Settings,
                                contentDescription = "Settings"
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                modifier = Modifier.align(Alignment.CenterVertically),
                                text = "Pengaturan & Edit Profile",
                                fontFamily = FontProvider.urbanist,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                } ?: run {
                    // Debugging text if userProfile is null
                    Text("User profile data is not available", color = Color.Red)
                }

                // Travel Containers
                listOf("Perjalanan seru bulan Juli!", "Perjalanan seru bulan Juli!").forEachIndexed { index, title ->
                    // "Daftar Perjalanan" text
                    Text(
                        text = "Daftar Perjalanan",
                        fontFamily = FontProvider.urbanist,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.Start)
                    )

                    // Travel Container
                    Column(
                        modifier = Modifier
                            .size(width = 400.dp, height = if (index == 0) 200.dp else 175.dp)
                            .fillMaxWidth()
                            .padding(16.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.White)
                            .border(
                                BorderStroke(2.dp, Color.Gray),
                                RoundedCornerShape(8.dp)
                            )
                            .padding(16.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.bodyLarge
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.35f),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            repeat(4) {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .fillMaxHeight()
                                        .padding(8.dp)
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(24.dp)
                                            .align(Alignment.Center),
                                        imageVector = Icons.Filled.Place,
                                        contentDescription = "Icon"
                                    )
                                }
                                if (it < 3) {
                                    Canvas(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .width(2.dp)
                                            .padding(horizontal = 4.dp)
                                    ) {
                                        val lineHeight = size.height * 0.5f
                                        val startY = (size.height - lineHeight) / 2
                                        val endY = startY + lineHeight
                                        drawLine(
                                            color = Color.Gray,
                                            start = Offset(0f, startY),
                                            end = Offset(0f, endY),
                                            strokeWidth = 2.dp.toPx()
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 5.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            OutlinedButton(
                                onClick = { /* Delete journey */ },
                                modifier = Modifier
                                    .size(width = 140.dp, height = 24.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black
                                ),
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    modifier = Modifier.size(24.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = "Hapus perjalanan",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                            OutlinedButton(
                                onClick = { /* View journey */ },
                                modifier = Modifier
                                    .size(width = 140.dp, height = 24.dp),
                                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = Color.White,
                                    contentColor = Color.Black
                                ),
                                border = BorderStroke(1.dp, Color.Black),
                                shape = RoundedCornerShape(5.dp)
                            ) {
                                Text("Lihat perjalananmu!", style = MaterialTheme.typography.bodySmall)
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Filled.Visibility,
                                    contentDescription = "View",
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                }

                // Logout Button
                OutlinedButton(
                    onClick = {
                        viewModel.logout(navController)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.Logout,
                        contentDescription = "Logout Icon",
                        modifier = Modifier.size(24.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Logout")
                }
            }

            // Loading Indicator
            if (isLoading) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    val context = LocalContext.current

    // Dummy user profile
    val dummyUserProfile = UserProfile().apply {
        id = 1
        name = "John Doe"
        email = "john.doe@example.com"
        phone = "+1234567890"
        avatar = "https://example.com/avatar.jpg"
        email_verified_at = "2024-08-01T12:00:00Z"
        role = "User"
        created_at = "2024-08-01T10:00:00Z"
        updated_at = "2024-08-02T10:00:00Z"
    }

    // Mock ViewModel
    val viewModel = object : AuthViewModel(context) {
        override val userProfile = MutableLiveData<UserProfile?>().apply {
            value = dummyUserProfile
        }
        override fun fetchUserProfile(signInMethod: SignInMethod) {
            userProfile.value = dummyUserProfile
        }
    }

    // Create a dummy NavController for preview
    val navController = rememberNavController()
    // Define dummy SignInMethod enum or class
    val signInMethod = SignInMethod.Manual

    // Call ProfileScreen with mock data
    ProfileScreen(
        navController = navController,
        viewModel = viewModel,
        onLogout = { /* Handle logout */ },
        signInMethod = signInMethod
    )
}
