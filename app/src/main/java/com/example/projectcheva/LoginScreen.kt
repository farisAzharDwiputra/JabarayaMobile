package com.example.projectcheva


import android.graphics.Color.BLACK
import android.graphics.Color.parseColor
import android.graphics.drawable.Icon
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ButtonDefaults.outlinedButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.ButtonBorder
import androidx.wear.compose.material.ButtonDefaults
import androidx.wear.compose.material.ExperimentalWearMaterialApi
import androidx.wear.compose.material.FractionalThreshold
import androidx.wear.compose.material.Icon
import androidx.wear.compose.material.OutlinedButton
import androidx.wear.compose.material.rememberSwipeableState
import androidx.wear.compose.material.swipeable
import org.w3c.dom.Text
import kotlin.math.roundToInt
import kotlin.math.sin
import androidx.compose.material3.OutlinedButton


@Composable
@ExperimentalWearMaterialApi
fun LoginScreen(){
    //Text login
    //Text Selamat datang kembali di jabaraya
    //gambar
    //Textfield email
    //Textfield password
    //Lupa kata sandi
    // login with google
    // login with facebook
    //button masuk

    //set default font menjadi urbanist
    val fontFamily = FontFamily(
        Font(R.font.urbanist_black, FontWeight.Black),
        Font(R.font.urbanist_bold,FontWeight.Bold),
        Font(R.font.urbanist_extrabold,FontWeight.ExtraBold),
        Font(R.font.urbanist_extralight,FontWeight.ExtraLight),
        Font(R.font.urbanist_light,FontWeight.Light),
        Font(R.font.urbanist_medium,FontWeight.Medium),
        Font(R.font.urbanist_regular,FontWeight.Normal),
        Font(R.font.urbanist_semibold,FontWeight.SemiBold),
        Font(R.font.urbanist_thin,FontWeight.Thin),
    )

    var teksEmail by remember{
        mutableStateOf("")
    }

    var teksPassword by remember{
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = "#c4e1ff".color)
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 60.dp)
                .padding(horizontal = 23.dp)
        )
        {
            Text(
                text = "LOGIN",
                color = Color.Black,
                fontSize = 32.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.ExtraBold
            )
            Text(

                text = "Selamat datang kembali",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
            Text(

                text = "di Jabaraya",
                color = Color.Black,
                fontSize = 20.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Medium
            )
        }
        Column (
            modifier = Modifier
                .fillMaxWidth()
        ){
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "",
                modifier = Modifier
                    .size(220.dp)
                    .align(AbsoluteAlignment.Right)
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Color.White,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                ),
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)
                    .padding(bottom = 22.dp)
                    .padding(horizontal = 15.dp),
                value = teksEmail,
                onValueChange = { teksBaru ->
                    teksEmail = teksBaru
                },
                label = {
                    Text(text = "Masukan Email")
                },
                isError = false,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                maxLines = 1,
                shape = RoundedCornerShape(20.dp)
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .padding(horizontal = 15.dp),
                value = teksPassword,
                onValueChange = { teksBaru ->
                    teksPassword = teksBaru
                },
                label = {
                    Text(text = "Masukan Password")
                },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(20.dp),
            )
            Text(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .padding(top = 2.dp)
                    .padding(bottom = 22.dp)
                    .align(AbsoluteAlignment.Right),
                text = "Lupa Kata Sandi?",
                color = Color.Black,
                fontSize = 13.sp,
                fontFamily = fontFamily,
                fontWeight = FontWeight.Normal
            )
            OutlinedButton(
                onClick = {/*TODO*/},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 15.dp)
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(20.dp),
                colors = outlinedButtonColors(
                    contentColor = Color.White // Warna latar belakang tombol
                ),
                border = BorderStroke(2.dp,"#D9D9D9".color)
            ){
                Column(

                ){
                    Icon (
                        painter = painterResource(id = R.drawable.icon_google),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .align(AbsoluteAlignment.Left)
                            .padding(end = 10.dp),
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = "Masuk dengan google",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                    )

            }
            OutlinedButton(
                onClick = {/*TODO*/},
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 5.dp)
                    .padding(horizontal = 15.dp),
                shape = RoundedCornerShape(20.dp),
                colors = outlinedButtonColors(
                    contentColor = Color.White // Warna latar belakang tombol
                ),
                border = BorderStroke(2.dp,"#D9D9D9".color)
            ){
                Column(

                ){
                    Icon (
                        painter = painterResource(id = R.drawable.icon_facebook),
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .align(AbsoluteAlignment.Left)
                            .padding(end = 10.dp),
                        tint = Color.Unspecified
                    )
                }
                Text(
                    text = "Masuk dengan facebook",
                    color = Color.Black,
                    fontSize = 16.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Normal
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            OutlinedButton(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 30.dp)
                    .padding(horizontal = 90.dp),
                shape = RoundedCornerShape(30.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = "#2F70B5".color// Warna latar belakang tombol
                ),
            ) {
                Text(
                    text = "Masuk",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
    /*
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = "#c4e1ff".color),
        verticalArrangement = Arrangement.Bottom,
    ){
        Spacer(modifier = Modifier.fillMaxWidth(1f))
        Text(
            /*modifier = Modifier
                .padding(vertical = 60.dp)
                .padding(horizontal = 23.dp),*/
            modifier = Modifier
                .padding(60.dp),
            text = "LOGIN",
            color = Color.Black,
            fontSize = 32.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.ExtraBold
        )
        Text(
            /*modifier = Modifier
                .padding(vertical = 0.dp)
                .padding(horizontal = 23.dp),*/
            text = "Selamat datang kembali di Jabaraya",
            color = Color.Black,
            fontSize = 31.sp,
            fontFamily = fontFamily,
            fontWeight = FontWeight.Normal
        )
        /*Image(
            painter = ,
            contentDescription ="",
            modifier = Modifier.size(40.dp)
            )*/
        Spacer(modifier = Modifier.fillMaxWidth(1f))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Color.White,
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
            ){
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    value = teksEmail,
                    onValueChange = { teksBaru ->
                        teksEmail = teksBaru
                    },
                        placeholder = {
                            Text(text = "Masukan Email")
                        },
                    shape = RoundedCornerShape(20.dp)
                    )

                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 80.dp),
                    value = teksPassword,
                    onValueChange = { teksBaru ->
                        teksPassword = teksBaru
                    },
                    placeholder = {
                        Text(text = "Masukan Password")
                    },
                    shape = RoundedCornerShape(20.dp)
                )
        }
    }*/


}

//hex warna menjadi string
val String.color
    get()= Color(parseColor(this))

@Preview
@Composable
@ExperimentalWearMaterialApi
fun LoginScreenPreview(){
    Surface {
        LoginScreen()
    }
}