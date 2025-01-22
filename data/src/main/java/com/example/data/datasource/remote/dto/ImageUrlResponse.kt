package com.example.data.datasource.remote.dto

import com.example.domain.model.ImageUrl

data class ImageUrlResponse(
    val imagesUrls: ImagesUrlsResponse,
    val id: Long
)

data class ImagesUrlsResponse(
    val entry: List<Entry>
)

data class Entry(
    val size: String?,
    val url: String?
)

fun ImageUrlResponse.toDomain(): List<ImageUrl> {
    return imagesUrls.entry.mapNotNull { entry ->
        if (entry.size != null && entry.url != null) {
            ImageUrl(
                size = entry.size,
                url = entry.url,
             )
        } else {
            null // Skip invalid entries with missing size or url
        }
    }
}