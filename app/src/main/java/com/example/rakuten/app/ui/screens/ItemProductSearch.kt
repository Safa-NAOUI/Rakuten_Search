package com.example.rakuten.app.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.domain.model.Product
import coil.compose.AsyncImage
import com.example.rakuten.app.ui.theme.customRed
import com.example.rakuten.app.util.StringConstants

@Composable
fun ProductDetailView(product: Product, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(1.dp)
            .clickable { onClick() }
            .padding(1.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 1.dp,
                    top = 1.dp,
                    end = 1.dp,
                    bottom = 4.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally // Alignement centré horizontalement
        ) {
            if (product.imagesUrls.isNotEmpty()) {
                AsyncImage(
                    model = product.imagesUrls[0],
                    contentDescription = "Image du produit",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Fit
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = product.headline,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontSize = 14.sp
                ),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                product.newBestPrice?.let {
                    Text(
                        text = StringConstants.NEW + " ${it}€",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = customRed,
                    )
                }
                product.usedBestPrice?.let {
                    Text(
                        text = "${StringConstants.USED} ${it}€",
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }
    }
}