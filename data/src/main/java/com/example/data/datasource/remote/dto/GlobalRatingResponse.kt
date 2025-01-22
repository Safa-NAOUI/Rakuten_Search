package com.example.data.datasource.remote.dto

import com.example.domain.model.GlobalRating

data class GlobalRatingResponse(
    val score: String,
    val nbReviews: String
) {
    fun toDomain(): GlobalRating {
        return GlobalRating(
            score = score.toDouble(),
            nbReviews = nbReviews.toInt()
        )
    }
}