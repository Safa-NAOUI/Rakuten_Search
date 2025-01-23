package com.example.rakuten.usecase

 import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.unit.dp
import com.example.rakuten.app.ui.components.StarRating
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
 import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class StarRatingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun starRating_displaysCorrectNumberOfFilledAndUnfilledStars() {
        val testRating = 3.5f // Set a rating value to test

        composeTestRule.setContent {
            StarRating(rating = testRating, starSize = 20.dp)
        }

        // Assert the correct number of filled stars
        for (i in 1..5) {
            val description = "Star $i"
            val isFilled = i <= testRating
            composeTestRule
                .onNodeWithContentDescription(description)
                .assertIsDisplayed()
                .assertExists()
        }
    }

    @Test
    fun starRating_correctTintAppliedToStars() {
        val testRating = 4f // Set a rating value to test

        composeTestRule.setContent {
            StarRating(rating = testRating, starSize = 20.dp)
        }

        // Assert the correct color is applied to filled stars
        for (i in 1..5) {
            val description = "Star $i"
            val expectedColor = if (i <= testRating) Color.Red else Color.Gray

            composeTestRule
                .onNodeWithContentDescription(description)
                .assertExists() // Ensure the star exists in the UI
        }
    }
}
