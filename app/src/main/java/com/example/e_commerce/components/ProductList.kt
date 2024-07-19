package com.example.e_commerce.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.e_commerce.network.Product

@Composable
fun ProductList(products: List<Product>) {
    LazyColumn {
        items(products) { product ->
            ProductItem(product)
        }
    }
}

@Composable
fun ProductItem(product: Product) {
    Row(modifier = Modifier.padding(8.dp)) {
        Image(
            painter = rememberAsyncImagePainter(model = product.image),
            contentDescription = product.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .padding(4.dp)
        )
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = product.title, color = Color.Black)
            Text(text = "$${product.price}", color = Color.Black)
        }
    }
}
