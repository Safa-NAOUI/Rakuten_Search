package com.example.data.datasource.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.data.datasource.local.entity.ProductDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDetailDao {

    /**
     * Retrieves the product details for the given product ID.
     * Returns null if the product is not found.
     *
     * @param id The unique product ID.
     * @return A Flow emitting the corresponding ProductDetailEntity or null.
     */

    @Query("SELECT * FROM product_details WHERE id = :id LIMIT 1")
    fun getProductDetail(id: String): Flow<ProductDetailEntity>
}
