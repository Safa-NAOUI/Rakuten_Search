package com.example.data.utils

import androidx.room.TypeConverter
import com.example.data.datasource.local.entity.SellerEntity


class SellerConverter {

    @TypeConverter
    fun fromSellerEntity(seller: SellerEntity): String {
        // Convert SellerEntity to String
        return "${seller.id},${seller.login}"
    }

    @TypeConverter
    fun toSellerEntity(data: String): SellerEntity {
        // Convert the string to SellerEntity
        val parts = data.split(",")
        return SellerEntity(parts[0].toLong(), parts[1])
    }
}