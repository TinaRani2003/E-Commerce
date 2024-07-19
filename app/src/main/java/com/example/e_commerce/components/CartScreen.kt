package com.example.e_commerce.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController

@Composable
fun CartScreen(navController: NavHostController) {
    Scaffold(
        topBar = { AppTopAppBar(title = "EZYDEALS", onAccountClick = { navController.navigate("profile") }) },
        bottomBar = { AppBottomNavigationBar(navController, selectedTab = 2) { /* Handle tab selection */ } }
    ) { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Text("This is the Cart Screen")
        }
    }
}
