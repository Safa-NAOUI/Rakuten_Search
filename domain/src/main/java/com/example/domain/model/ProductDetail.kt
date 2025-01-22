package com.example.domain.model

/**
 * Created by Safa NAOUI on 19,January,2025
 */

data class ProductDetail(
    val id: String,
    val title: String,
    val description: String,
    val newPrice: String,
    val usedPrice: String?,
    val images: List<String>,
    val rating: Float,
    val reviewCount: Int,
    val seller: Seller,
    val quality: String,
    val type: String,
    val sellerComment: String,
    val categories: List<String>
)