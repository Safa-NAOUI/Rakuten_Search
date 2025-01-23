package com.example.rakuten.app.ui.components

import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rakuten.app.util.StringConstants

@Composable
fun ProductPriceInfo(
    newBestPrice: Double,
    usedBestPrice: Double,
    customRed: Color,
    alignment: Alignment.Horizontal
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = alignment,
        verticalArrangement = Arrangement.spacedBy(1.dp)
    ) {
        Text(
            text = "${StringConstants.NEW} ${newBestPrice}€",
            fontWeight = FontWeight.Bold,
            fontSize = 14.sp,
            color = customRed,
        )
        Text(
            text = "${StringConstants.USED} ${usedBestPrice}€",
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            color = Color.Gray,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}
