package com.example.projectcheva.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MainLayout() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
    ) {
        ProfileContainer(
            image = Icons.Filled.Person,
            username = "John Doe",
            email = "johndoe@example.com",
            phoneNumber = "+123456789"
        )
        Spacer(modifier = Modifier.height(16.dp))
        Container3()
        Spacer(modifier = Modifier.height(16.dp))
        ButtonWithArrow()
        Spacer(modifier = Modifier.height(16.dp))
        ButtonWithIcon(icon = Icons.Filled.Edit)
    }
}

@Composable
fun ProfileContainer(image: ImageVector, username: String, email: String, phoneNumber: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(RoundedCornerShape(10.75.dp))
            .background(Color.LightGray)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(imageVector = image, contentDescription = null, modifier = Modifier.size(80.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = username, fontSize = 20.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = email, fontSize = 16.sp)
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = phoneNumber, fontSize = 16.sp)
    }
}

@Composable
fun Container3() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(10.75.dp))
            .border(
                BorderStroke(1.dp, Color.LightGray),
            )
            .padding(16.dp),
    ) {
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            InfoRow(title = "Nama")
            InfoRow(title = "Email")
            InfoRow(title = "No Hp.")
        }
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedButton(
            onClick = { /* Handle button click */ },
            modifier = Modifier
                .align(Alignment.End),
            border = BorderStroke(1.dp, Color.LightGray)
        ) {
            Icon(
                imageVector = Icons.Filled.Edit,
                contentDescription = null,
                tint = Color.Black
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Edit",
                color = Color.Black
            )
        }
    }
}

@Composable
fun InfoRow(title: String) {
    Row(
        modifier = Modifier
            .background(Color.White)
            .clip(RoundedCornerShape(10.75.dp))
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Filled.Edit, contentDescription = null, modifier = Modifier.padding(end = 8.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = Color.LightGray, RoundedCornerShape(8.dp))
                .padding(1.dp)
                .clip(RoundedCornerShape(10.75.dp))
        ) {
            BasicTextField(
                value = title,
                onValueChange = {},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(1.dp)
            )
        }
    }
}

@Composable
fun ButtonWithArrow() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.275f),
            border = BorderStroke(1.dp, Color.LightGray),
            onClick = { /* Handle button click */ }) {
            Icon(
                imageVector =Icons.Filled.Edit,
                contentDescription = null,
                tint = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Ganti Password", color = Color.Black)
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
            {
                OutlinedButton(
                    onClick = { /* Handle small button click */ },
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(0.5f)
                        .align(Alignment.CenterEnd)
                ) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Filled.ArrowDropUp,
                        contentDescription = null,
                        tint = Color.Black)
                }
            }
        }
    }
}

@Composable
fun ButtonWithIcon(icon: ImageVector) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(0.75f),
            border = BorderStroke(1.dp, Color.LightGray),
            onClick = { /* Handle button click */ }) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Black)
            Spacer(
                modifier = Modifier
                    .width(8.dp)
            )
            Text("Button", color = Color.Black)
        }
    }
}

@Preview
@Composable
fun MainPreview(){
    MainLayout()
}