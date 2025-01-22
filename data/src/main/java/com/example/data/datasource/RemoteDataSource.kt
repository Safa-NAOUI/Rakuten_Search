package com.example.data.datasource

import com.example.domain.model.Product

/**
 * RemoteDataSource is an interface that defines methods for fetching product data
 * from a remote API or network source. This abstraction helps in separating network
 * operations from business logic, improving maintainability and testability.
 */

interface RemoteDataSource {
    suspend fun searchProducts(keyword: String): List<Product>
}