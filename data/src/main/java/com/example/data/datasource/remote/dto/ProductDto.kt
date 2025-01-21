package com.example.data.datasource.remote.dto

/**
 * Created by Safa NAOUI on 20,January,2025
 */

import com.example.domain.model.Product

/**
 * Data Transfer Object (DTO) representing a product retrieved from the API.
 */

data class ProductDto(
    val id: String,
    val headline: String,
    val newBestPrice: Double?,
    val usedBestPrice: Double?,
    val imagesUrls: List<String>,
    val score: Float?,
    val nbReviews: Int?
) {
    fun toDomain(): Product {
        return Product(
            id = id,
            headline = headline,
            newBestPrice = newBestPrice,
            usedBestPrice = usedBestPrice,
            imagesUrls = imagesUrls,
            description = "",
            score = score ?: 0f,
            nbReviews = nbReviews ?: 0
        )
    }
}