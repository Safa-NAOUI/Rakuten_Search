package com.example.rakuten.app.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.domain.model.GlobalRating
import com.example.domain.model.ImageUrl
import com.example.domain.model.ProductDetail
import com.example.domain.model.Seller
import com.example.rakuten.app.ui.components.CustomTopAppBar
import com.example.rakuten.app.ui.components.DisplayCustomHtml
import com.example.rakuten.app.ui.components.ImageCarouselWithIndicator
import com.example.rakuten.app.ui.components.StarRating
import com.example.rakuten.app.ui.theme.customRed
import com.example.rakuten.app.util.StringConstants
import com.example.rakuten.app.util.StringConstants.SELLER_REVIEW

@Composable
fun ItemProductDetail(
    product: ProductDetail,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        // Top App Bar

        CustomTopAppBar(
            title = "",
            onBackClick = onBackClick
        )
        // Carousel for images
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
                .padding(top = 8.dp)
        ) {

            ImageCarouselWithIndicator(images = product.images)
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Product details
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = product.headline,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                // StarRating inside the column
                StarRating(rating = product.globalRating.score.toFloat(), starSize = 20.dp)
                product.globalRating.nbReviews.let {
                    Text(
                        text = "${it} ${StringConstants.REVIEW}",
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = customRed,
                    )
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                product.newBestPrice?.let {
                    Text(
                        text = "${StringConstants.NEW} ${it}€",
                        fontWeight = FontWeight.Bold,
                        fontSize = 14.sp,
                        color = customRed,
                    )
                }
                product.usedBestPrice?.let {
                    Text(
                        text = "${StringConstants.USED} ${it}€", fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                product.seller.login.let {
                    Text(
                        text = StringConstants.SOLD_BY + it,
                        fontWeight = FontWeight.Normal,
                        fontSize = 12.sp,
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))

            DisplayCustomHtml(product.description)

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp, bottom = 10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(1.dp)
            ) {
                Text(
                    text = SELLER_REVIEW,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = " \" " + product.sellerComment + " \" - " + product.seller.login,
                    fontWeight = FontWeight.Normal,
                    fontStyle = FontStyle.Italic, // Italique
                    fontSize = 12.sp,
                    color = Color.DarkGray,
                    style = MaterialTheme.typography.bodyMedium
                )

            }
        }
    }
}


// Mock Product for Preview
fun getMockProduct(): ProductDetail {
    return ProductDetail(
        headline = "Samsung Galaxy S21",
        description = "<p>This is a <strong>great</strong> product description with <em>HTML</em> tags.</p>",
        images = listOf(
            ImageUrl(
                size = "large",
                url = "https://fr.shopping.rakuten.com/photo/1673299896_S.jpg"
            ),
            ImageUrl(size = "small", url = "https://fr.shopping.rakuten.com/photo/1673299896_S.jpg")
        ),
        productId = 111,
        salePrice = 799.99,
        newBestPrice = 749.99,
        usedBestPrice = 699.99,
        quality = "New",
        type = "Smartphone",
        sellerComment = "Brand new, sealed box",
        categories = listOf("Electronics", "Smartphones"),
        globalRating = GlobalRating(1.0, 5),
        seller = Seller(100, "test"),
    )
}

@Preview(showBackground = true)
@Composable
fun ProductDetailContentPreview() {
    ItemProductDetail(
        product = getMockProduct(),
        onBackClick = { }
    )
}

