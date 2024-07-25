package com.example.e_commerce

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.components.CartScreen
import com.example.e_commerce.components.CategoryScreen
import com.example.e_commerce.components.ForgotPasswordScreen
import com.example.e_commerce.components.HomeScreen
import com.example.e_commerce.components.LoginScreen
import com.example.e_commerce.components.ProductDetails
import com.example.e_commerce.components.ProfileScreen
import com.example.e_commerce.components.SignupScreen
import com.example.e_commerce.components.WelcomeScreen
import com.example.e_commerce.ui.theme.ECommerceTheme
import com.example.e_commerce.viewmodel.CartViewModel
import com.example.e_commerce.viewmodel.ProductViewModel
import com.example.e_commerce.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(applicationContext)
    }
    private val cartViewModel: CartViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECommerceApp(productViewModel, cartViewModel)
        }
    }
}


@Composable
fun ECommerceApp(productViewModel: ProductViewModel, cartViewModel: CartViewModel) {
    val navController = rememberNavController()
    ECommerceTheme {
        NavHost(navController = navController, startDestination = "welcome") {
            composable("welcome") { WelcomeScreen(navController) }
            composable("signup") { SignupScreen(navController) }
            composable("login") { LoginScreen(navController) }
            composable("forgot password") { ForgotPasswordScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("categories") { CategoryScreen(navController, productViewModel) }
            composable("cart") { CartScreen(navController, cartViewModel) }
            composable("profile") { ProfileScreen(navController) }
            composable("product_details/{productId}") { backStackEntry ->
                val productId = backStackEntry.arguments?.getString("productId")
                val product = productViewModel.getProductById(productId)
                ProductDetails(navController, product, cartViewModel)
            }
        }
    }
}




