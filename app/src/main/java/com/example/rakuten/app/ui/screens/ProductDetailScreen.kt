package com.example.rakuten.app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.utils.DataResult
import com.example.rakuten.app.viewmodel.ProductViewModel

@Composable
fun DetailScreen(
    productId: String,
    navController: NavController,
    viewModel: ProductViewModel = hiltViewModel()){
    val productState by viewModel.selectedProduct.collectAsState()


    LaunchedEffect(productId) {
        viewModel.getProductDetail(productId)
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        when (val result = productState) {
            is DataResult.Loading -> {
                CircularProgressIndicator()
            }

            is DataResult.Success -> {
                ItemProductDetail(
                    product = result.data,
                    onBackClick = {  navController.popBackStack() })
            }

            is DataResult.Error -> {
                Text(text = "Error: ${result.message}") //, color = MaterialTheme.colors.error)
            }
        }
    }
}
