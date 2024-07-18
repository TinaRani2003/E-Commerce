package com.example.e_commerce

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddShoppingCart
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ProductionQuantityLimits
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.VerifiedUser
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.AddShoppingCart
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.ProductionQuantityLimits
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.VerifiedUser
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.e_commerce.components.ForgotPasswordScreen
import com.example.e_commerce.components.HomeScreen
import com.example.e_commerce.components.LoginScreen
import com.example.e_commerce.components.ProductsScreen
import com.example.e_commerce.components.SignupScreen
import com.example.e_commerce.components.WelcomeScreen
import com.example.e_commerce.ui.theme.BlueDark
import com.example.e_commerce.ui.theme.ECommerceTheme
import com.example.e_commerce.viewmodel.ProductViewModel
import com.example.e_commerce.viewmodel.ProductViewModelFactory

class MainActivity : ComponentActivity() {
    private val productViewModel: ProductViewModel by viewModels {
        ProductViewModelFactory(applicationContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            ECommerceApp(productViewModel)
            ECommerceTheme {

                var selected by remember {
                    mutableIntStateOf(0)
                }
                Scaffold(
                    bottomBar = {
                        NavigationBar{
                            bottomNavItems.forEachIndexed { index, bottomNavItem ->
                                NavigationBarItem(
                                    selected = index == selected,
                                    onClick = {
                                        selected = index
//                                        navController.navigate(bottomNavItem.route)
                                    },
                                    icon = {
                                        BadgedBox(
                                            badge = {
                                                if (bottomNavItem.badges != 0) {
                                                Badge{
                                                    Text(
                                                        text = bottomNavItem.badges.toString()
                                                    )
                                                }
                                            } else if (bottomNavItem.hasNews) {
                                                Badge()
                                            }
                                    }
                                        ) {
                                            Icon(
                                                imageVector = if (index == selected) bottomNavItem.selectedIcon else bottomNavItem.unselectedIcon,
                                                contentDescription = bottomNavItem.title
                                            )
                                        }
                                    },
                                    label = {
                                        Text(text = bottomNavItem.title)
                                    }
                                )
                            }
                        }
                    },
                    topBar = {
                        HomeTopAppBar()
                    }
                ){
                    val padding = it

                    Column {
                        Spacer(modifier = Modifier.height(60.dp))
                        ProductsScreen(
                            navController = rememberNavController(),
                            productViewModel = productViewModel
                        )
                    }
                }
            }
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
            IconButton(onClick = { Toast.makeText(context, "Notifications", Toast.LENGTH_SHORT).show() }) {
                Icon(imageVector = Icons.Filled.Notifications, contentDescription = "Cart", tint = Color.White)
            }
        }
    )
}



val bottomNavItems = listOf(
    BottomNavItem(
        title = "Home",
        route = "HomeScreen",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
        badges = 0
    ),
    BottomNavItem(
        title = "Catagory",
        route = "Catagory",
        selectedIcon = Icons.Filled.Category,
        unselectedIcon = Icons.Outlined.Category,
        hasNews = false,
        badges = 0
    ),
    BottomNavItem(
        title = "Cart",
        route = "Cart",
        selectedIcon = Icons.Filled.ShoppingCart,
        unselectedIcon = Icons.Outlined.ShoppingCart,
        hasNews = false,
        badges = 0
    ),
    BottomNavItem(
        title = "Profile",
        route = "Profile",
        selectedIcon = Icons.Filled.AccountCircle,
        unselectedIcon = Icons.Outlined.AccountCircle,
        hasNews = false,
        badges = 0
    )
)

data class BottomNavItem(
    val title: String,
    val route: String,
    val selectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val unselectedIcon: androidx.compose.ui.graphics.vector.ImageVector,
    val hasNews: Boolean,
    val badges: Int
)

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