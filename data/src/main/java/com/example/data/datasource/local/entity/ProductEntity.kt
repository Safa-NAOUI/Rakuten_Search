package com.example.data.datasource.local.entity

/**
 * Created by Safa NAOUI on 20,January,2025
 */

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.utils.Converters

/**
 * Entity representing a product in the local Room database.
 */

@Entity(tableName = "products")
@TypeConverters(Converters::class)
data class ProductEntity(
    @PrimaryKey val id: String,
    val headline: String,
    val newBestPrice: Double?,
    val usedBestPrice: Double?,
    val imageUrls: List<String>,
    val score: Float?,
    val nbReviews: Int?
)
