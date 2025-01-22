package com.example.data.datasource.local.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.data.utils.SellerConverter
import com.example.data.utils.Converters
import com.example.data.utils.ImageUrlConverter

/**
 * Entity representing product details in the local Room database.
 */

@Entity(tableName = "product_details")
@TypeConverters(Converters::class, SellerConverter::class, ImageUrlConverter::class)
data class ProductDetailEntity(
    @PrimaryKey
    val productId: Long,

    val headline: String,
    val description: String,

    val salePrice: Double,
    val newBestPrice: Double,
    val usedBestPrice: Double?,

    val quality: String,
    val type: String,
    val sellerComment: String,

    @TypeConverters(Converters::class)
    val categories: List<String>,

    @Embedded
    val globalRating: GlobalRatingEntity,

    @Embedded
    val seller: SellerEntity,

    @TypeConverters(ImageUrlConverter::class)
    val images: List<ImageUrlEntity>
)