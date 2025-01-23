package com.example.rakuten.app.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.model.Product
import com.example.domain.utils.DataResult
import com.example.rakuten.app.ui.components.ErrorState
import com.example.rakuten.app.ui.components.LoadingState
import com.example.rakuten.app.ui.components.SearchBar
import com.example.rakuten.app.ui.components.SuccessState
import com.example.rakuten.app.viewmodel.ProductViewModel

/**
 * Created by Safa NAOUI on 21,January,2025
 */

@Composable
fun SearchScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val productListState by viewModel.products.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        SearchBar(
            searchQuery = searchQuery,
            onSearchQueryChanged = { newQuery -> viewModel.setSearchQuery(newQuery) },
            onSearch = { viewModel.searchProducts(searchQuery) },
            onClear = { viewModel.clearProducts() },
            label = "Search for a product"
        )

        Spacer(modifier = Modifier.height(16.dp))

        when (productListState) {
            is DataResult.Loading -> LoadingState()
            is DataResult.Success -> {
                val productList = (productListState as DataResult.Success<List<Product>>).data
                SuccessState(productList, navController)
            }
            is DataResult.Error -> {
                val errorMessage = (productListState as DataResult.Error).message
                ErrorState(errorMessage)
            }
        }
    }
}