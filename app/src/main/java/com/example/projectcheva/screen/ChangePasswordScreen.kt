package com.example.projectcheva.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.projectcheva.FontProvider
import com.example.projectcheva.PasswordFieldType
import com.example.projectcheva.R
import com.example.projectcheva.retrofit.AuthViewModel

@Composable
fun ChangePasswordScreen(navController: NavController, viewModel: AuthViewModel, onPasswordChanged: (Boolean, String) -> Unit) {

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    val confirmPassword by remember { mutableStateOf("") }

    // State to manage which password field is being interacted with
    var visibleField by remember { mutableStateOf<PasswordFieldType?>(null) }

    // Determine which icon to use based on the visible field state
    fun getIconForField(type: PasswordFieldType) = when (type) {
        PasswordFieldType.CURRENT -> if (visibleField == PasswordFieldType.CURRENT) R.drawable.eye_regular else R.drawable.eye_slash_regular
        PasswordFieldType.NEW -> if (visibleField == PasswordFieldType.NEW) R.drawable.eye_regular else R.drawable.eye_slash_regular
        PasswordFieldType.CONFIRM -> if (visibleField == PasswordFieldType.CONFIRM) R.drawable.eye_regular else R.drawable.eye_slash_regular
    }

    val context = LocalContext.current
    //Main Layout
    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = "#68B1FF".color)
    ) {
        //Sub-layout 1
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.75f),
            contentAlignment = Alignment.BottomEnd
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(220.dp)
                    .padding(start = 24.dp)
            )
        }
        //Sub-layout 2
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .weight(1f)
                .align(Alignment.CenterHorizontally)
                .background(
                    color = Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
        )
        {

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Buat Password Baru",
                color = "#474646".color,
                fontSize = 24.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Pastikan password berbeda dari password sebelumnya.",
                color = "#474646".color,
                fontSize = 12.sp,
                fontFamily = FontProvider.urbanist,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.align(Alignment.CenterHorizontally))

            Column(
                modifier = Modifier
                    .padding(top = 24.dp, bottom = 16.dp, start = 16.dp, end = 16.dp)
                    .fillMaxSize()
            ) {

                Spacer(modifier = Modifier.height(24.dp))

                Text(
                    "Masukkan Password Baru",
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.SemiBold)

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = currentPassword,
                    onValueChange = { currentPassword = it },
                    label = { Text("Password Saat Ini") },
                    shape = RoundedCornerShape(20.dp),
                    visualTransformation = if (visibleField == PasswordFieldType.NEW) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock Icon")
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(0.075f)
                                .padding(end = 8.dp)
                                .clickable {
                                    // Toggle the visibility state when clicked
                                    visibleField = if (visibleField == PasswordFieldType.NEW) null else PasswordFieldType.NEW
                                },
                            painter = painterResource(id = getIconForField(PasswordFieldType.NEW)),
                            contentDescription = if (visibleField == PasswordFieldType.NEW) "Hide Password" else "Show Password")
                        },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedLabelColor = Color.Gray,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,),
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    "Konfirmasi Password Baru",
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.SemiBold)

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = newPassword,
                    onValueChange = { newPassword = it },
                    label = { Text("Password Baru") },
                    shape = RoundedCornerShape(20.dp),
                    visualTransformation = if (visibleField == PasswordFieldType.CONFIRM) VisualTransformation.None else PasswordVisualTransformation(),
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.Lock, contentDescription = "Lock Icon")
                    },
                    trailingIcon = {
                        Icon(
                            modifier = Modifier
                                .fillMaxSize(0.075f)
                                .padding(end = 8.dp)
                                .clickable {
                                    // Toggle the visibility state when clicked
                                    visibleField = if (visibleField == PasswordFieldType.CONFIRM) null else PasswordFieldType.CONFIRM
                                },
                            painter = painterResource(id = getIconForField(PasswordFieldType.CONFIRM)),
                            contentDescription = if (visibleField == PasswordFieldType.CONFIRM) "Hide Password" else "Show Password")
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        unfocusedLabelColor = Color.Gray,
                        focusedBorderColor = Color.Black,
                        unfocusedBorderColor = Color.Black,),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(32.dp))

                OutlinedButton(
                    onClick = {
                        if (newPassword == confirmPassword) {
                            viewModel.changePassword(currentPassword, newPassword, confirmPassword) { success, message ->
                                onPasswordChanged(success, message)
                                if (success) {
                                    navController.popBackStack()
                                } else {
                                    // Show error message
                                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            // Show error message for password mismatch
                            Toast.makeText(context, "Passwords do not match", Toast.LENGTH_SHORT).show()
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .align(Alignment.CenterHorizontally),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF2F70B5)
                    )
                ) {
                    Text("Change Password")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview() {
    ChangePasswordScreen(
        navController = NavController(LocalContext.current),
        viewModel = AuthViewModel(LocalContext.current),
        onPasswordChanged = { _, _ -> }
    )
}
