package com.example.rakuten.usecase

import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import com.example.rakuten.app.ui.components.SearchBar
import com.example.rakuten.app.util.ErrorMessages
import org.junit.Rule
import org.junit.Test

class SearchBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun searchBar_showsErrorWhenEmptyOnSearch() {
        composeTestRule.setContent {
            SearchBar(
                searchQuery = "",
                onSearchQueryChanged = {},
                onSearch = {},
                onClear = {},
                label = "Rechercher"
            )
        }

        // Perform the search action (simulate IME action Search)
        composeTestRule.onNodeWithText("Rechercher").performImeAction()

        // Assert that error message is displayed
        composeTestRule.onNodeWithText(ErrorMessages.EMPTY_FIELD_ERROR)
            .assertExists()
            .assertIsDisplayed()
    }

    @Test
    fun searchBar_clearsQueryWhenClearButtonClicked() {
        var searchQuery = "Test Query"
        composeTestRule.setContent {
            SearchBar(
                searchQuery = searchQuery,
                onSearchQueryChanged = { newQuery -> searchQuery = newQuery },
                onSearch = {},
                onClear = { searchQuery = "" },
                label = "Rechercher"
            )
        }

        // Assert the text field shows the initial query
        composeTestRule.onNodeWithText("Test Query").assertExists()

        // Click the clear button
        composeTestRule.onNodeWithContentDescription("Effacer").performClick()

        // Assert the text field is now empty
        composeTestRule.onNodeWithText("Test Query").assertDoesNotExist()
    }

    @Test
    fun searchBar_triggersOnSearchWhenValid() {
        var isSearchTriggered = false
        composeTestRule.setContent {
            SearchBar(
                searchQuery = "Valid Query",
                onSearchQueryChanged = {},
                onSearch = { isSearchTriggered = true },
                onClear = {},
                label = "Rechercher"
            )
        }

        // Perform the search action (simulate IME action Search)
        composeTestRule.onNodeWithText("Rechercher").performImeAction()

        // Assert that the search action was triggered
        assert(isSearchTriggered)
    }
}