package com.example.rakuten.app.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.rakuten.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val alpha: Float by animateFloatAsState(
        targetValue = 1f,
        animationSpec = androidx.compose.animation.core.tween(durationMillis = 1000) // 1 second fade-in
    )

    // Simulate a delay for the splash screen, then navigate to the main screen
    LaunchedEffect(Unit) {
        delay(2000) // Show splash screen for 2 seconds
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        // Image with fade-in effect
        Image(
            painter = painterResource(id = R.drawable.logo_rakuten),
            contentDescription = "Splash Logo",
            modifier = Modifier.alpha(alpha)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    val navController = rememberNavController()

    SplashScreen(navController = navController) // This will show a preview without actual navigation
}
