package com.example.data.datasource.remote.dto

import com.example.domain.model.ProductDetail

/**
 * Created by Safa NAOUI on 20,January,2025
 */

/**
 * Data model representing the product details response from the API.
 */

data class ProductDetailsResponse(
    val productId: String,
    val headline: String,
    val description: String,
    val newBestPrice: String,
    val usedBestPrice: String?,
    val quality: String,
    val type: String,
    val sellerComment: String,
    val categories: List<String>,
) {
    fun toDomain(): ProductDetail {
        return ProductDetail(
            productId = productId,
            headline = headline,
            description = description,
            newBestPrice = newBestPrice,
            usedBestPrice = usedBestPrice,
            quality=quality,
            type= type,
            sellerComment=sellerComment,
            categories= categories
        )
    }
}
