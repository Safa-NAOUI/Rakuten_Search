package com.example.data.mapper

import com.example.data.datasource.local.entity.ProductDetailEntity
import com.example.data.datasource.local.entity.ProductEntity
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import com.example.domain.model.Seller
import com.example.data.datasource.local.entity.SellerEntity

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
