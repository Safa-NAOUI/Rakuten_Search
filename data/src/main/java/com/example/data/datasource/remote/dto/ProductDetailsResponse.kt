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
    val title: String,
    val description: String,
    val newPrice: String,
    val usedPrice: String?,
    val images: List<String>,
    val rating: Float,
    val reviewCount: Int,
    val seller: SellerResponse,
    val quality: String,
    val type: String,
    val sellerComment: String,
    val categories: List<String>
) {
    fun toDomain(): ProductDetail {
        return ProductDetail(
            id = id,
            title = title,
            description = description,
            newPrice = newPrice,
            usedPrice = usedPrice,
            images = images,
            rating = rating,
            reviewCount = reviewCount,
            seller = seller.toDomain(),
            quality = quality,
            type = type,
            sellerComment = sellerComment,
            categories = categories
        )
    }
}
