package com.example.e_commerce.components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserSessionViewModel : ViewModel() {
    fun logout(onLogoutComplete: () -> Unit) {
        viewModelScope.launch {
            onLogoutComplete()
        }
    }
}
