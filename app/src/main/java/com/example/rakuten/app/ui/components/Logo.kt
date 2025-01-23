package com.example.rakuten.app.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.rakuten.R

@Composable
fun Logo(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo_rakuten),
            contentDescription = "logo",
            modifier = Modifier
                .size(150.dp)
                .align(Alignment.Center)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun LogoPreview() {
    Logo()
}