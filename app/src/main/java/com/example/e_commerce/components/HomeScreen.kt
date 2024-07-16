package com.example.e_commerce.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.example.e_commerce.HomeTopAppBar
import com.example.e_commerce.network.Product
import com.example.e_commerce.viewmodel.ProductViewModel

@Composable
fun HomeScreen(navController: NavHostController, productViewModel: ProductViewModel) {
    val products by productViewModel.products.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        HomeTopAppBar()
        LazyColumn {
            items(products) { product ->
                ProductItem(product)
            }
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberImagePainter(data = product.image),
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .padding(4.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = product.title)
            Text(text = "$${product.price}")
        }
    }
}
