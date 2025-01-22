package com.example.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_urls")
data class ImageUrlEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
     val size: String,
    val url: String
)