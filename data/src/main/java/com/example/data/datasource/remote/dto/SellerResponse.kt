package com.example.data.datasource.remote.dto

import com.example.domain.model.Seller

data class SellerResponse(
    val id: String,
    val login: String
) {
    fun toDomain(): Seller {
        return Seller(
            id = id.toLong(),
            login = login
        )
    }
}