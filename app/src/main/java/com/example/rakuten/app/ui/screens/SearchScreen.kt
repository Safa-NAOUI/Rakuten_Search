package com.example.rakuten.app.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.domain.model.Product
import com.example.domain.utils.DataResult
import com.example.rakuten.app.ui.components.ErrorState
import com.example.rakuten.app.ui.components.Logo
import com.example.rakuten.app.ui.components.SearchBar
import com.example.rakuten.app.ui.components.SuccessState
import com.example.rakuten.app.viewmodel.ProductViewModel

/**
 * Created by Safa NAOUI on 21,January,2025
 */

/** SearchScreen : This Composable function represents the UI for the search screen.
 * - Displays a search bar for users to enter product queries.
 * - Handles search query changes, initiates product searches, and clears search results.
 * - Displays a loading indicator while search results are being fetched.
 * - Renders a list of search results upon successful retrieval.
 * - Displays an error message if any issues occur during the search process.
 **/

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(navController: NavController, viewModel: ProductViewModel = hiltViewModel()) {
    val searchQuery by viewModel.searchQuery.collectAsState()
    val productListState by viewModel.products.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("") },
                navigationIcon = { Logo(Modifier.fillMaxSize()) }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 90.dp, start = 10.dp, end = 10.dp)
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
                    is DataResult.Loading -> {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(50.dp),
                                color = Color.Blue,
                                strokeWidth = 4.dp
                            )
                        }
                    }
                    is DataResult.Success -> {
                        val productList =
                            (productListState as DataResult.Success<List<Product>>).data
                        SuccessState(productList, navController)
                    }

                    is DataResult.Error -> {
                        val errorMessage = (productListState as DataResult.Error).message
                        ErrorState(errorMessage)
                    }
                }
            }
        }
    )
}