package com.example.data.datasource.remote

import com.example.data.datasource.RemoteDataSource
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import javax.inject.Inject

/**
 * Remote data source responsible for fetching product data from the API.
 */

class RemoteProductDataSource @Inject constructor(
    private val api: ProductApi
) : RemoteDataSource {

    override suspend fun searchProducts(keyword: String): List<Product> {
        return api.searchProducts(keyword).products.map { it.toDomain() }
    }

    override suspend fun getProductDetails(id: String): ProductDetail {
        return api.getProductDetails(id).toDomain()
    }
}
