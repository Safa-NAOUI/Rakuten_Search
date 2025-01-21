package com.example.domain.repository

/**
 * Created by Safa NAOUI on 19,January,2025
 */

import com.example.domain.DataResult
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    /** Searches for products based on the given keyword.**/
    fun searchProducts(keyword: String): Flow<DataResult<List<Product>>>

    /** Retrieves detailed information for a specific product.**/
    fun getProductDetails(id: String): Flow<DataResult<ProductDetail>>
}