package com.example.domain.model

/**
 * Created by Safa NAOUI on 19,January,2025
 */
data class ProductDetail(
    val id: String, // Unique identifier for the product
    val title: String, // Title of the product
    val description: String, // Detailed description of the product
    val newPrice: String, // Price of the product new
    val usedPrice: String?, // Price of the product used (optional)
    val images: List<String>, // List of URLs for product images
    val rating: Float, // Average rating of the product
    val reviewCount: Int // Number of reviews for the product
)