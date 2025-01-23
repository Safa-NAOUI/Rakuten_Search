package com.example.rakuten.app.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import com.example.domain.usecase.GetListProductUseCase
import com.example.domain.utils.DataResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getListProductUseCase: GetListProductUseCase
) : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    private val _selectedProduct = MutableStateFlow<DataResult<ProductDetail>>(DataResult.Loading)
    val selectedProduct: StateFlow<DataResult<ProductDetail>> = _selectedProduct.asStateFlow()

    private val _products = MutableStateFlow<DataResult<List<Product>>>(DataResult.Success(emptyList()))
    val products: StateFlow<DataResult<List<Product>>> = _products.asStateFlow()

    private val _hasSearched = MutableStateFlow(false)

    fun searchProducts(keyword: String) {
        _products.value = DataResult.Loading
        viewModelScope.launch {
            getListProductUseCase.searchProduct(keyword)
                .flowOn(Dispatchers.IO)
                .onStart {
                    // Emit loading state
                    _products.value = DataResult.Loading
                }
                .catch { e ->
                    handleError("searchProducts", e)
                }
                .collectLatest { productList ->
                    _products.value = if (productList.isEmpty()) {
                        DataResult.Success(emptyList())
                    } else {
                        DataResult.Success(productList)
                    }
                }
        }
    }

    private fun handleError(source: String, e: Throwable) {
        _products.value = DataResult.Error("Erreur de réseau : ${e.localizedMessage}")
    }

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun clearProducts() {
        _searchQuery.value = ""
        _products.value = DataResult.Success(emptyList())
        _hasSearched.value = false
    }

    fun getProductDetail(productId: String) {
        viewModelScope.launch {
            getListProductUseCase.getProductDetails(productId)
                .flowOn(Dispatchers.IO)
                .filterNotNull()
                .onStart {
                    // Emit loading state
                    _selectedProduct.value = DataResult.Loading
                }
                .catch { e ->
                    handleProductDetailError(e)
                }
                .collectLatest { product ->
                         _selectedProduct.value = DataResult.Success(product)
                }
        }
    }

    private fun handleProductDetailError(e: Throwable) {
         _selectedProduct.value = DataResult.Error("Erreur de réseau : ${e.localizedMessage}")
    }
}
