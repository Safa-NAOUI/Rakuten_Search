package com.example.data.datasource.local

import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.local.dao.ProductDao
import com.example.data.mapper.toDomain
import com.example.domain.model.Product
import kotlinx.coroutines.flow.first

import javax.inject.Inject


class LocalProductDataSource @Inject constructor(
    private val productDao: ProductDao
) : LocalDataSource {

    /**
     * Searches for products in the local database based on the provided keyword.
     * Returns a list of matching products.
     */
    override suspend fun searchProducts(keyword: String): List<Product> {
        return productDao.getAllProducts(keyword).first().map { it.toDomain() }
    }
}
