package com.example.rakuten.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.example.domain.model.ImageUrl
import com.example.rakuten.R

@Composable
fun ImageCarouselWithIndicator(images: List<ImageUrl>) {
    if (images.isEmpty()) {
        Text(text = "No images available", modifier = Modifier.fillMaxSize())
        return
    }
    val originalImages = images.filter { it.size == "ORIGINAL" } // Filtered list of original images
    // State for the pager
    // Set up pager state with the size of the filtered list
    val pagerState = rememberPagerState(
        initialPage = 0, // Starting page
        initialPageOffsetFraction = 0f, // Offset for the starting page
        pageCount = { originalImages.size } // Total number of pages matches filtered list
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) { page ->
            Image(
                painter = rememberAsyncImagePainter(
                    ImageRequest.Builder(LocalContext.current)
                        .data(data = originalImages[page].url)
                        .apply {
                            placeholder(R.drawable.no_image)
                            error(R.drawable.no_image)
                        }
                        .build()
                ),
                contentDescription = "Product Image",
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Dots Indicator
        DotsIndicator(
            totalDots = originalImages.size,
            selectedIndex = pagerState.currentPage,
            selectedColor = Color.Red,
            unselectedColor = Color.Gray,
            selectedSize = 18,
            unselectedSize = 16,
            spacing = 4
        )

    }
}
