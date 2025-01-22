package com.example.rakuten.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.rakuten.app.ui.screens.DetailScreen
import com.example.rakuten.app.ui.screens.SearchScreen

@Composable
fun RakutenNav() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "search") {
        composable("search") { SearchScreen(navController) }
        composable("details/{productId}") { backStackEntry ->
            val productId = backStackEntry.arguments?.getString("productId") ?: ""
            DetailScreen(productId = productId)
        }
    }
}
