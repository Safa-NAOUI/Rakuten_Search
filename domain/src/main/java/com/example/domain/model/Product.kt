package com.example.domain.model

/**
 * Created by Safa NAOUI on 19,January,2025
 */
data class Product(
    val id: String, // Unique identifier for the product
    val headline: String, // Short title or headline for the product
    val newBestPrice: Double?, // Best new price for the product (nullable)
    val usedBestPrice: Double?, // Best used price for the product (nullable)
    val imagesUrls: List<String>, // List of URLs for product images
    val description: String, // Short description of the product
    val score: Float, // Overall score or rating of the product
    val nbReviews: Int // Number of reviews for the product
)