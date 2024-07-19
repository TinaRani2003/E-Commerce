package com.example.e_commerce.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController) {
    // Track the selected tab
    var selectedTab by remember { mutableIntStateOf(3) } // 3 corresponds to the Profile tab

    Scaffold(
        topBar = { AppTopAppBar(title = "EZYDEALS", onAccountClick = { /* No additional action needed */ }) },
        bottomBar = {
            AppBottomNavigationBar(
                navController = navController,
                selectedTab = selectedTab,
                onTabSelected = { tabIndex ->
                    selectedTab = tabIndex
                    // Navigate to the selected screen
                    navController.navigate(bottomNavItems[tabIndex].route) {
                        // Optional: clear back stack if needed
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("This is the Profile Screen")
        }
    }
}
