package com.example.data.mapper

import com.example.data.datasource.local.entity.ProductDetailEntity
import com.example.data.datasource.local.entity.ProductEntity
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail

/** Mapper `ProductEntity` -> `Product`**/
fun ProductEntity.toDomain(): Product {
    return Product(
        id = id,
        headline = headline,
        newBestPrice = newBestPrice,
        usedBestPrice = usedBestPrice,
        imagesUrls = imageUrls,
        description = "",
        score = score ?: 0f,
        nbReviews = nbReviews ?: 0
    )
}

/** Mapper `Product` -> `ProductEntity` **/
fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        headline = headline,
        newBestPrice = newBestPrice,
        usedBestPrice = usedBestPrice,
        imageUrls = imagesUrls,
        score = score,
        nbReviews = nbReviews
    )
}

/** Mapper `ProductDetailEntity` -> `ProductDetail` **/
fun ProductDetailEntity.toDomain(): ProductDetail {
    return ProductDetail(
        id = id,
        title = title,
        description = description,
        newPrice = newPrice,
        usedPrice = usedPrice,
        images = images,
        rating = rating,
        reviewCount = reviewCount
    )
}

/** Mapper `ProductDetail` -> `ProductDetailEntity` **/
fun ProductDetail.toEntity(): ProductDetailEntity {
    return ProductDetailEntity(
        id = id,
        title = title,
        description = description,
        newPrice = newPrice,
        usedPrice = usedPrice,
        images = images,
        rating = rating,
        reviewCount = reviewCount
    )
}
