package com.example.rakuten.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rakuten.app.ui.screens.DetailScreen
import com.example.rakuten.app.ui.screens.SearchScreen
import com.example.rakuten.app.ui.screens.SplashScreen

@Composable
fun RakutenNav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            SplashScreen(navController = navController)
        }
        composable("home") {
            SearchScreen(navController = navController)
        }
        composable("details/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            if (productId.isEmpty()) {
                navController.popBackStack()
            } else {
                DetailScreen(productId = productId, navController = navController)
            }
        }
    }
}