package com.example.rakuten.app.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun StarRating(
    rating: Float, // The current rating (0.0 to 5.0)
    starSize: Dp = 20.dp,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            val filled = i <= rating // Check if this star is filled based on rating
            Icon(
                imageVector = if (filled) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Star $i",
                modifier = Modifier.size(starSize),
                tint = if (filled) Color.Red else Color.Gray
            )
        }
    }
}
