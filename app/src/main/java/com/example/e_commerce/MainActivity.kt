package com.example.e_commerce


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECommerceApp()
        }
    }


    @Composable
    fun ECommerceApp() {
        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "welcome") {
            composable("welcome") { EzyDealsWelcomeScreen(navController) }
            composable("signup") { SignupScreen(navController) }
            composable("login"){LoginScreen(navController)}
        }
    }
}