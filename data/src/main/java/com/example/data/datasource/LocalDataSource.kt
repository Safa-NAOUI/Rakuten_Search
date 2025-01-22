package com.example.data.datasource

import com.example.domain.model.Product

/**
 * LocalDataSource is an interface that defines methods for accessing product data stored locally,
 * typically in a Room database. This abstraction helps in separating data access logic from the repository.
 */

interface LocalDataSource {
    suspend fun searchProducts(keyword: String): List<Product>
}