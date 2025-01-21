package com.example.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.utils.Converters

/**
 * Entity representing product details in the local Room database.
 */

@Entity(tableName = "product_details")
@TypeConverters(Converters::class)
data class ProductDetailEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String,
    val newPrice: String,
    val usedPrice: String?,
    val images: List<String>,
    val rating: Float,
    val reviewCount: Int
)
