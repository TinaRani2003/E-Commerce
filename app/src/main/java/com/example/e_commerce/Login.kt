package com.example.e_commerce

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun LoginScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent) // Make the background transparent
    ) {
        Image(
            painter = painterResource(id = R.drawable.cart_image), // Replace with your background image
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize()
                .alpha(0.2f)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier

                .fillMaxWidth()
                .padding(top = 100.dp)
        ) {
            Text(
                text = "Access your",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = " EZYDEALS",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "Perks",
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = TextStyle(
                    textAlign = TextAlign.Center
                )
            )




            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = "",
                onValueChange = { },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp)
                    .padding(end = 16.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { passwordVisible = !passwordVisible }) {
                        Icon(
                            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
                            contentDescription = "Toggle password visibility"
                        )
                    }
                },
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp)
                    .padding(end = 16.dp)
            )


            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF6D3D)),
                shape = RoundedCornerShape(50),
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 16.dp)
                    .padding(end = 16.dp)
            ) {
                Text(
                    text = "LOGIN",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Don't have an account? ",
                    fontSize = 16.sp,
                    color = Color.Black
                )
                Text(
                    text = "Sign up",
                    fontSize = 16.sp,
                    color = Color.Blue,
                    modifier = Modifier.clickable(onClick = { navController.navigate("signup") })
                )
            }
        }
    }
}