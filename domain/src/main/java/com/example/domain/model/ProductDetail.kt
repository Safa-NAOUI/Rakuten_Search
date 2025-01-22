package com.example.domain.model

/**
 * Created by Safa NAOUI on 19,January,2025
 */

data class ProductDetail(
    val productId: Long,
    val headline: String,
    val description: String,
    val salePrice: Double,
    val newBestPrice: Double,
    val usedBestPrice: Double?,
    val quality: String,
    val type: String,
    val sellerComment: String,
    val categories: List<String>,
    val globalRating: GlobalRating,
    val seller: Seller,
    val images: List<ImageUrl>
)

data class GlobalRating(
    val score: Double,
    val nbReviews: Int
)

data class ImageUrl(
    val size: String,
    val url: String
)