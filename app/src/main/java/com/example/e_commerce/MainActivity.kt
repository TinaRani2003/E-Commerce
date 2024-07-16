package com.example.e_commerce

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.components.ForgotPasswordScreen
import com.example.e_commerce.components.HomeScreen
import com.example.e_commerce.components.LoginScreen
import com.example.e_commerce.components.SignupScreen
import com.example.e_commerce.components.WelcomeScreen
import com.example.e_commerce.ui.theme.BlueDark
import com.example.e_commerce.viewmodel.ProductViewModel
import com.example.e_commerce.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ECommerceApp(productViewModel)
        }
    }
}

// HomeTopAppBar
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    val context = LocalContext.current.applicationContext
    TopAppBar(
        title = { Text(text = "EzyDeals") },
        navigationIcon = {
            IconButton(onClick = { Toast.makeText(context, "EzyDeals", Toast.LENGTH_SHORT).show() }) {
                Icon(painter = painterResource(id = R.drawable.cart_image), contentDescription = "EzyDealsIcon")
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = BlueDark,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = { Toast.makeText(context, "Search", Toast.LENGTH_SHORT).show() }) {
                Icon(imageVector = Icons.Filled.Search, contentDescription = "Search", tint = Color.White)
            }
            IconButton(onClick = { Toast.makeText(context, "Cart", Toast.LENGTH_SHORT).show() }) {
                Icon(imageVector = Icons.Filled.ShoppingCart, contentDescription = "Cart", tint = Color.White)
            }
            IconButton(onClick = { Toast.makeText(context, "More", Toast.LENGTH_SHORT).show() }) {
                Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "Profile", tint = Color.White)
            }
        }
    )
}

@Composable
fun ECommerceApp(productViewModel: ProductViewModel) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") { WelcomeScreen(navController) }
        composable("signup") { SignupScreen(navController) }
        composable("login") { LoginScreen(navController) }
        composable("home") { HomeScreen(navController, productViewModel) }
        composable("forgotpassword") { ForgotPasswordScreen(navController) }
    }
}
