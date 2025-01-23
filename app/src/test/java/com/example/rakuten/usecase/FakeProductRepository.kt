package com.example.rakuten.usecase

import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import com.example.domain.repository.ProductRepository
import com.example.domain.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FakeProductRepository @Inject constructor() : ProductRepository {

    private val searchResults = mutableMapOf<String, Flow<DataResult<List<Product>>>>()

    override fun searchProducts(keyword: String): Flow<DataResult<List<Product>>> {
        return searchResults[keyword] ?: flowOf(DataResult.Error("No mock data for this keyword"))
    }

    override fun getProductDetails(id: String): Flow<DataResult<ProductDetail>> {
        TODO("Not yet implemented")
    }

    fun mockSearchResults(keyword: String, products: List<Product>) {
        searchResults[keyword] = flowOf(DataResult.Success(products))
    }

    fun mockError(keyword: String, errorMessage: String) {
        searchResults[keyword] = flowOf(DataResult.Error(errorMessage))
    }
}
