package com.example.projectcheva.screen

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
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
fun EditPassword (navController: NavController, viewModel: AuthViewModel, onPasswordChanged: (Boolean, String) -> Unit){

    var currentPassword by remember { mutableStateOf("") }
    var newPassword by remember { mutableStateOf("") }
    val confirmPassword by remember { mutableStateOf("") }

    // State to manage which password field is being interacted with
    var visibleField by remember { mutableStateOf<PasswordFieldType?>(null) }

    // State variable to manage visibility of the content
    var isContentVisible by remember { mutableStateOf(false) }

    // Determine which icon to use based on the visible field state
    fun getIconForField(type: PasswordFieldType) = when (type) {
        PasswordFieldType.CURRENT -> if (visibleField == PasswordFieldType.CURRENT) R.drawable.eye_regular else R.drawable.eye_slash_regular
        PasswordFieldType.NEW -> if (visibleField == PasswordFieldType.NEW) R.drawable.eye_regular else R.drawable.eye_slash_regular
        PasswordFieldType.CONFIRM -> if (visibleField == PasswordFieldType.CONFIRM) R.drawable.eye_regular else R.drawable.eye_slash_regular
    }

    val context = LocalContext.current

    //MainLayout
    Column(
    modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
        .padding(8.dp)
    )
    {
        //Header
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.075f),
            contentAlignment = Alignment.TopCenter
        ) {
            Column {
                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Buat Password Baru",
                    color = "#474646".color,
                    fontSize = 24.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.ExtraBold,
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    text = "Pastikan password berbeda dari password sebelumnya.",
                    color = "#474646".color,
                    fontSize = 12.sp,
                    fontFamily = FontProvider.urbanist,
                    fontWeight = FontWeight.Medium,
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
        }
        //Profile Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.375f)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp
                    )
                )
                .border(
                    BorderStroke(2.dp, Color.Gray),
                    RoundedCornerShape(8.dp)
                )
                .align(Alignment.CenterHorizontally)
        ){
            Spacer(modifier = Modifier.height(24.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.Start),
                //verticalAlignment = Alignment.CenterVertically
            ){
                Image(
                    painterResource(id = R.drawable.logo),
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(128.dp)
                        .border(
                            BorderStroke(2.dp, Color.Gray),
                            RoundedCornerShape(100.dp)
                        )
                        .clip(CircleShape)
                        .align(Alignment.CenterVertically)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ){
                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = "Nama",
                        fontFamily = FontProvider.urbanist,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color.Black
                    )
                    Text(
                        modifier = Modifier
                            .align(Alignment.Start)
                            .padding(top = 16.dp),
                        text = "Profile",
                        fontFamily = FontProvider.urbanist,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black
                    )
                }
            }
            Box(
                modifier = Modifier
                    .padding(bottom = 12.dp, end = 12.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.BottomEnd
            ){
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
                        .align(Alignment.BottomEnd)
                        .fillMaxHeight(1f)
                        .fillMaxWidth(0.65f),
                    shape = RoundedCornerShape(30.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    )
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable {
                                isContentVisible = !isContentVisible
                            }
                            .align(Alignment.CenterVertically)
                            .size(16.dp),
                        imageVector = Icons.Filled.Settings,
                        tint = Color.Black,
                        contentDescription = "Settings"
                    )
                    Spacer(modifier = Modifier.width(18.dp))

                    Text(
                        "Pengaturan & Edit Profile",
                        fontFamily = FontProvider.urbanist,
                        fontSize = 12.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterVertically))

                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        //Travel Container
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.45f)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp
                    )
                )
                .border(
                    BorderStroke(2.dp, Color.Gray),
                    RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.TopStart
            ){
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White)
                        .align(Alignment.Center)
                ){

                    Text(
                        modifier = Modifier.align(Alignment.Start),
                        text = "Perjalanan seru bulan Juli!")

                    Spacer(modifier = Modifier.height(12.dp))

                    Row(
                        modifier = Modifier
                            .border(
                                BorderStroke(1.dp, Color.Gray),
                                RoundedCornerShape(8.dp)
                            )
                            .fillMaxWidth()
                            .fillMaxHeight(0.5f)
                        ,
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
                                    tint = Color.Black,
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
                    Spacer(modifier = Modifier.height(12.dp))
                    Row(
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .fillMaxHeight(0.5f),
                            contentAlignment = Alignment.BottomStart
                        ){
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
                                    .align(Alignment.TopStart)
                                    .fillMaxWidth(0.75f)
                                    .fillMaxHeight(0.5f),
                                shape = RoundedCornerShape(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .clickable {
                                            isContentVisible = !isContentVisible
                                        }
                                        .align(Alignment.CenterVertically)
                                        .size(16.dp),
                                    imageVector = Icons.Filled.Settings,
                                    tint = Color.Black,
                                    contentDescription = "Settings"
                                )

                                Spacer(modifier = Modifier.width(18.dp))

                            }

                            Text(
                                "Pengaturan & Edit Profile",
                                fontFamily = FontProvider.urbanist,
                                fontSize = 5.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center)
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(0.5f),
                            contentAlignment = Alignment.BottomEnd
                        ){
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
                                    .align(Alignment.BottomEnd)
                                    .fillMaxSize(),
                                shape = RoundedCornerShape(30.dp),
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                )
                            ) {
                                Icon(
                                    modifier = Modifier
                                        .clickable {
                                            isContentVisible = !isContentVisible
                                        }
                                        .align(Alignment.CenterVertically)
                                        .size(16.dp),
                                    imageVector = Icons.Filled.Settings,
                                    tint = Color.Black,
                                    contentDescription = "Settings"
                                )
                                Spacer(modifier = Modifier.width(18.dp))

                                Text(
                                    "Pengaturan & Edit Profile",
                                    fontFamily = FontProvider.urbanist,
                                    fontSize = 12.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.align(Alignment.CenterVertically)
                                )
                            }
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.45f)
                .background(
                    Color.White,
                    shape = RoundedCornerShape(
                        topStart = 30.dp,
                        topEnd = 30.dp,
                        bottomEnd = 30.dp,
                        bottomStart = 30.dp
                    )
                )
                .border(
                    BorderStroke(2.dp, Color.Gray),
                    RoundedCornerShape(8.dp)
                )
                .padding(8.dp)
                .align(Alignment.CenterHorizontally)
        ){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally)
            ){
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.Start),
                ){
                    Image(
                        painterResource(id = R.drawable.logo),
                        contentDescription = "User Avatar",
                        modifier = Modifier
                            .size(128.dp)
                            .border(
                                BorderStroke(2.dp, Color.Gray),
                                RoundedCornerShape(100.dp)
                            )
                            .clip(CircleShape)
                            .align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column (
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                    ){
                        Text(
                            modifier = Modifier.align(Alignment.Start),
                            text = "Nama",
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color.Black
                        )
                        Text(
                            modifier = Modifier
                                .align(Alignment.Start)
                                .padding(top = 16.dp),
                            text = "Profile",
                            fontFamily = FontProvider.urbanist,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.Black
                        )
                    }
                }
                Row(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Box(
                        modifier = Modifier.fillMaxHeight(0.25f),
                        contentAlignment = Alignment.BottomStart){
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
                                .align(Alignment.BottomEnd)
                                .fillMaxHeight(0.5f)
                                .fillMaxWidth(0.5f),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            )
                        ) {
                            Icon(
                                modifier = Modifier
                                    .clickable {
                                        isContentVisible = !isContentVisible
                                    }
                                    .align(Alignment.CenterVertically)
                                    .size(16.dp),
                                imageVector = Icons.Filled.Settings,
                                tint = Color.Black,
                                contentDescription = "Settings"
                            )
                            Spacer(modifier = Modifier.width(18.dp))

                            Text(
                                "Pengaturan & Edit Profile",
                                fontFamily = FontProvider.urbanist,
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Box(
                        modifier = Modifier.fillMaxHeight(0.25f),
                        contentAlignment = Alignment.BottomEnd
                    ){
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
                                .align(Alignment.BottomEnd)
                                .fillMaxHeight(1f)
                                .fillMaxWidth(0.65f),
                            shape = RoundedCornerShape(30.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.White
                            )
                        ) {
                            Icon(
                                modifier = Modifier
                                    .clickable {
                                        isContentVisible = !isContentVisible
                                    }
                                    .align(Alignment.CenterVertically)
                                    .size(16.dp),
                                imageVector = Icons.Filled.Settings,
                                tint = Color.Black,
                                contentDescription = "Settings"
                            )
                            Spacer(modifier = Modifier.width(18.dp))

                            Text(
                                "Pengaturan & Edit Profile",
                                fontFamily = FontProvider.urbanist,
                                fontSize = 12.sp,
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                    }
                }
            }
        }
        // AnimatedVisibility to handle the sliding animation
        AnimatedVisibility(
            visible = isContentVisible,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 900)
            ) + fadeIn(animationSpec = tween(durationMillis = 900)),
            exit = slideOutVertically(
                targetOffsetY = { it },
                animationSpec = tween(durationMillis = 900)
            ) + fadeOut(animationSpec = tween(durationMillis = 900))
        ) {
            // Content that should be shown/hidden
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.75f)
                    .background(
                        Color.Cyan,
                        shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                    )
                    .align(Alignment.CenterHorizontally)
                    .padding(12.dp)
            )
            {
                Spacer(modifier = Modifier.height(8.dp))
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
                                    visibleField =
                                        if (visibleField == PasswordFieldType.NEW) null else PasswordFieldType.NEW
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
                                    visibleField =
                                        if (visibleField == PasswordFieldType.CONFIRM) null else PasswordFieldType.CONFIRM
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
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun editPreview(){
    EditPassword(
        navController = NavController(LocalContext.current),
        viewModel = AuthViewModel(LocalContext.current),
        onPasswordChanged = { _, _ -> })
}
