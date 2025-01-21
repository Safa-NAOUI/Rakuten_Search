package com.example.data.datasource.local

import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.local.dao.ProductDao
import com.example.data.datasource.local.dao.ProductDetailDao
import com.example.data.mapper.toDomain
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull

import javax.inject.Inject


class LocalProductDataSource @Inject constructor(
    private val productDao: ProductDao,
    private val productDetailDao: ProductDetailDao
) : LocalDataSource {

    /**
     * Searches for products in the local database based on the provided keyword.
     * Returns a list of matching products.
     */
    override suspend fun searchProducts(keyword: String): List<Product> {
        return productDao.getAllProducts(keyword).first().map { it.toDomain() }
    }

    /**
     * Retrieves product details from the local database based on the product ID.
     * Throws an exception if the product is not found.
     */
    override suspend fun getProductDetails(id: String): ProductDetail {
        return productDetailDao.getProductDetail(id).firstOrNull()?.toDomain()
            ?: throw Exception("Produit introuvable")
    }
}
