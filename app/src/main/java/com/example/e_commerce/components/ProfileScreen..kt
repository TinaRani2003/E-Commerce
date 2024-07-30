package com.example.e_commerce.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun ProfileScreen(navController: NavHostController, userSessionViewModel: UserSessionViewModel = viewModel()) {
    // Track the selected tab
    var selectedTab by remember { mutableIntStateOf(3) } // 3 corresponds to the Profile tab

    // Observe user details from the view model
    val user by userSessionViewModel.user.observeAsState()

    Scaffold(
        topBar = { AppTopAppBar(title = "EZYDEALS", onAccountClick = { /* No additional action needed */ }) },
        bottomBar = {
            AppBottomNavigationBar(
                navController = navController,
                selectedTab = selectedTab,
                onTabSelected = { tabIndex ->
                    selectedTab = tabIndex
                    // Navigate to the selected screen
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            Text("Profile", style = MaterialTheme.typography.headlineMedium)

            Spacer(modifier = Modifier.height(16.dp))

            // User Information
            ProfileSectionTitle("User Information")
            user?.let { UserInfo(it) } ?: Text("Loading...")

            Spacer(modifier = Modifier.height(16.dp))

            // Order History
            ProfileSectionTitle("Order History")
            OrderHistory()

            Spacer(modifier = Modifier.height(16.dp))

            // Addresses
            ProfileSectionTitle("Address")
            Address()

            Spacer(modifier = Modifier.height(16.dp))

            // Account Settings
            ProfileSectionTitle("Account Settings")
            AccountSettings(
                navController = navController,
                userSessionViewModel = userSessionViewModel
            )
        }
    }
}

@Composable
fun ProfileSectionTitle(title: String) {
    Text(title, style = MaterialTheme.typography.titleLarge)
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        color = Color.Gray
    )
}

@Composable
fun UserInfo(user: User) {
    var userName by remember { mutableStateOf(TextFieldValue(user.name)) }
    var userEmail by remember { mutableStateOf(TextFieldValue(user.email)) }
    var userPhone by remember { mutableStateOf(TextFieldValue(user.phoneNumber)) }

    Column {
        ProfileTextField(label = "Name", value = userName, onValueChange = { userName = it })
        ProfileTextField(label = "Email", value = userEmail, onValueChange = { userEmail = it })
        ProfileTextField(label = "Phone", value = userPhone, onValueChange = { userPhone = it })
    }
}

@Composable
fun ProfileTextField(label: String, value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    )
}

@Composable
fun OrderHistory() {
    Column {
        OrderHistoryItem("Order 1")
    }
}

@Composable
fun OrderHistoryItem(orderId: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(orderId, style = MaterialTheme.typography.bodyLarge)
    }
}

@Composable
fun Address() {
    Column {
        AddressItem("Address", "Enter your address")
    }
}

@Composable
fun AddressItem(addressType: String, address: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Text(addressType, style = MaterialTheme.typography.bodyLarge)
        Text(address, style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
    }
}

@Composable
fun AccountSettings(navController: NavHostController, userSessionViewModel: UserSessionViewModel) {
    Column {
        AccountSettingsItem("Change Password", onClick = { /* Handle Change Password */ })
        AccountSettingsItem("Logout", onClick = {
            userSessionViewModel.logout {
                navController.navigate("login") {
                    popUpTo("profile") { inclusive = true }
                }
            }
        })
    }
}

@Composable
fun AccountSettingsItem(option: String, onClick: () -> Unit) {
    Text(
        text = option,
        style = MaterialTheme.typography.bodyLarge,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable(onClick = onClick)
    )
}
