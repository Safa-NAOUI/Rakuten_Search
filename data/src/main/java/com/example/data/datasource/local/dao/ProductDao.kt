package com.example.data.datasource.local.dao

/**
 * Created by Safa NAOUI on 20,January,2025
 */

import androidx.room.Dao
import androidx.room.Query
import com.example.data.datasource.local.entity.ProductDetailEntity
import com.example.data.datasource.local.entity.ProductEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    /**
     * Searches for products by keyword in the headline (case-insensitive).
     * Returns a Flow with the matching products.
     */

    @Query("SELECT * FROM products WHERE LOWER(headline) LIKE LOWER('%' || :keyword || '%')")
    fun getAllProducts(keyword: String): Flow<List<ProductEntity>>

    @Query("SELECT * FROM product_details WHERE productId = :id LIMIT 1")
    fun getProductDetail(id: String): Flow<ProductDetailEntity>

}