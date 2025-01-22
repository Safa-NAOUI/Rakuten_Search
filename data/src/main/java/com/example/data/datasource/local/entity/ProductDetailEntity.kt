package com.example.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.utils.ConverteSeller
import com.example.data.utils.Converters

/**
 * Entity representing product details in the local Room database.
 */

@Entity(tableName = "product_details")
@TypeConverters(Converters::class, ConverteSeller::class)
data class ProductDetailEntity(
    @PrimaryKey val productId: String,
    val headline: String,
    val description: String,
    val newBestPrice: String,
    val usedBestPrice: String?,
   val quality: String,
   val type: String,
   val sellerComment: String,
   val categories: List<String>,
)
