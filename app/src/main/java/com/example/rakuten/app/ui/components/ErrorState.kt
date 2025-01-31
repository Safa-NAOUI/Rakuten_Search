package com.example.rakuten.app.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun ErrorState(errorMessage: String) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(
            text = "Error: $errorMessage",
            color = Color.Red,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
@Preview(showBackground = true)
@Composable
fun ErrorStatePreview() {
    ErrorState(errorMessage = "An error occurred")
}
