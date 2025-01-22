package com.example.data.mapper

import com.example.data.datasource.local.entity.ProductDetailEntity
import com.example.data.datasource.local.entity.ProductEntity
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import com.example.domain.model.Seller
import com.example.domain.model.GlobalRating
import com.example.domain.model.ImageUrl
import com.example.data.datasource.local.entity.SellerEntity
import com.example.data.datasource.local.entity.GlobalRatingEntity
import com.example.data.datasource.local.entity.ImageUrlEntity
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
        productId = productId,
        headline = headline,
        description = description,
        newBestPrice = newBestPrice,
        usedBestPrice = usedBestPrice,
        quality=quality,
        type= type,
        sellerComment=sellerComment,
        categories= categories,
        salePrice = salePrice,
        globalRating = globalRating.toDomain(),
        seller = seller.toDomain(),
        images = images.mapNotNull { it.toDomain() }?: emptyList()
    )
}

/** Mapper `ProductDetail` -> `ProductDetailEntity` **/
fun ProductDetail.toEntity(): ProductDetailEntity {
    return ProductDetailEntity(
        productId = productId,
        headline = headline,
        description = description,
        newBestPrice = newBestPrice,
        usedBestPrice = usedBestPrice,
        quality=quality,
        type= type,
        sellerComment=sellerComment,
        categories= categories,
        salePrice = salePrice,
        globalRating = globalRating.toEntity(),
        seller = seller.toEntity(),
        images = images.mapNotNull { it.toEntity() }

    )
}


/** Mapper `SellerEntity` -> `Seller` **/
fun SellerEntity.toDomain(): Seller {
    return Seller(
        id = id,
        login = login
    )
}

/** Mapper `Seller` -> `SellerEntity` **/
fun Seller.toEntity(): SellerEntity {
    return SellerEntity(
        id = id,
        login = login
    )
}


/** Mapper `globalRatingEntity` -> `Seller` **/
fun GlobalRatingEntity.toDomain(): GlobalRating {
    return GlobalRating(
        score  = score,
    nbReviews = nbReviews
    )
}

/** Mapper `globalRating` -> `globalRatingEntity` **/
fun GlobalRating.toEntity(): GlobalRatingEntity {
    return GlobalRatingEntity(
        score  = score,
        nbReviews = nbReviews
    )
}


/** Mapper `globalRatingEntity` -> `Seller` **/
fun ImageUrlEntity.toDomain(): ImageUrl {
    println("ImageUrlEntity size: $size, url: $url") // Log values to check if they are null
    return ImageUrl(
        size  = size?: "UNKNOWN_SIZE",
        url = url?: "UNKNOWN_URL"
    )
}

/** Mapper `globalRating` -> `globalRatingEntity` **/
fun ImageUrl.toEntity(): ImageUrlEntity {
    return ImageUrlEntity(
        url  = url,
        size = size
    )
}