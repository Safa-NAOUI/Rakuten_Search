package com.example.data.datasource.remote.dto

import com.example.domain.model.ProductDetail

/**
 * Created by Safa NAOUI on 20,January,2025
 */

/**
 * Data model representing the product details response from the API.
 */

data class ProductDetailsResponse(
    val id: String,
    val headline: String,
    val description: String,
    val imagesUrls: List<String>,
    val newBestPrice: Double?,
    val usedBestPrice: Double?,
    val score: Float?,
    val nbReviews: Int?
) {
    fun toDomain(): ProductDetail {
        return ProductDetail(
            id = id,
            title = headline,
            description = description,
            newPrice = newBestPrice?.toString() ?: "N/A",
            usedPrice = usedBestPrice?.toString(),
            images = imagesUrls,
            rating = score ?: 0f,
            reviewCount = nbReviews ?: 0
        )
    }
}
