package com.example.data.datasource.remote.dto

import com.example.domain.model.ImageUrl
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
    val salePrice: String,
    val newBestPrice: String,
    val usedBestPrice: String?,
    val quality: String,
    val type: String,
    val sellerComment: String,
    val categories: List<String>,
    val globalRating: GlobalRatingResponse,
    val seller: SellerResponse,
    val images: List<ImageUrlResponse>
) {
    fun toDomain(): ProductDetail {
        return ProductDetail(
            productId = productId.toLong(),
            headline = headline,
            description = description,
            salePrice = salePrice.toDouble(),
            newBestPrice = newBestPrice.toDouble(),
            usedBestPrice = usedBestPrice?.toDouble(),
            quality = quality,
            type = type,
            sellerComment = sellerComment,
            categories = categories,
            globalRating = globalRating.toDomain(),
            seller = seller.toDomain(),
            images = images.flatMap { imageUrlResponse ->
                imageUrlResponse.imagesUrls.entry.mapNotNull { entry ->
                    if (entry.size != null && entry.url != null) {
                        ImageUrl(
                            size = entry.size,
                            url = entry.url,
                        )
                    } else {
                        null // Skip invalid images with null size or url
                    }
                }
            }
        )
    }
}