package com.example.data.datasource.remote.dto

import com.example.domain.model.Seller

class SellerResponse(
    val id: Long,
    val login: String
) {
    fun toDomain(): Seller {
        return Seller(
            id = id,
            login = login
        )
    }
}