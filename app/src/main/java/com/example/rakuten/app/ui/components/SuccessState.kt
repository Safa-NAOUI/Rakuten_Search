package com.example.rakuten.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.domain.model.Product

@Composable
fun SuccessState(productList: List<Product>, navController: NavController) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        if (productList.isEmpty()) {
            Text(
                text = "Search list is empty, try searching.",
                color = Color.Gray,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(productList) { product ->
                    ProductItem(
                        product = product,
                        onClick = { navController.navigate("details/${product.id}") }
                    )
                }
            }
        }
    }
}
