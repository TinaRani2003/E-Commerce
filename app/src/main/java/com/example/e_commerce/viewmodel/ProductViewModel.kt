package com.example.e_commerce.viewmodel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.e_commerce.network.Product
import com.example.e_commerce.network.RetrofitInstance
import com.example.e_commerce.utils.NetworkUtils
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

class ProductViewModel(private val context: Context) : ViewModel() {

    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> get() = _products

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        if (!NetworkUtils.isNetworkAvailable(context)) {
            Toast.makeText(context, "No internet connection", Toast.LENGTH_SHORT).show()
            return
        }

        viewModelScope.launch {
            try {
                val productsList = RetrofitInstance.api.getProducts()
                _products.value = productsList
            } catch (e: IOException) {
                Toast.makeText(context, "Failed to fetch products. Please try again.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}