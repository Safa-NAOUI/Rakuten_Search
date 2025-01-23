package com.example.rakuten.app.viewmodel

/**
 * Created by Safa NAOUI on 19,January,2025
 */

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import com.example.domain.usecase.GetListProductUseCase
import com.example.domain.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getListProductUseCase: GetListProductUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedProduct = MutableStateFlow<DataResult<ProductDetail>>(DataResult.Loading)
    val selectedProduct: StateFlow<DataResult<ProductDetail>> = _selectedProduct

    private val _products = MutableStateFlow<DataResult<List<Product>>>(DataResult.Loading)
    val products: StateFlow<DataResult<List<Product>>> = _products.asStateFlow()

    // Fetches the list of products based on the search keyword
    fun searchProducts(keyword: String) {
        viewModelScope.launch {
            try {

                getListProductUseCase.searchProduct(keyword).flowOn(Dispatchers.IO)
                    .collectLatest { productList ->
                        _products.value = if (productList.isEmpty()) {
                            DataResult.Error("No products found")
                        } else {
                            DataResult.Success(productList)
                        }
                    }
            } catch (e: Exception) {
                Log.e("searchProducts", "Error fetching product list", e)
                _products.value = DataResult.Error(e.toString())
            }
        }
    }

    // Fetches product details for a given product ID
    fun getProductDetail(productId: String) {
        viewModelScope.launch {

            _selectedProduct.value = DataResult.Loading

            try {
                getListProductUseCase.getProductDetails(productId).flowOn(Dispatchers.IO)
                    .collectLatest { product ->

                        product?.let {
                            _selectedProduct.value = DataResult.Success(it)
                        } ?: run {
                            _selectedProduct.value = DataResult.Error("Product is null")
                        }
                    }
            } catch (e: Exception) {
                Log.e("getProductDetail", "Error fetching product detail", e)
                _selectedProduct.value =
                    DataResult.Error(e.toString())
            }
        }
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun clearProducts() {
        _searchQuery.value = ""
        _products.value = DataResult.Success(emptyList())
    }
}