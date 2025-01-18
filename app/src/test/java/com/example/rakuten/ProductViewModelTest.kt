package com.example.rakuten

/**
 * Created by Safa NAOUI on 18, January, 2025
 */

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull

@ExperimentalCoroutinesApi
class ProductViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule() // Allows LiveData to be executed on the main thread during tests

    private lateinit var viewModel: ProductViewModel
    private val mockUseCase: GetProductsUseCase = mock() // Create a mock for GetProductsUseCase

    @Before
    fun setup() {
        // Initialize the dispatcher for coroutines
        Dispatchers.setMain(UnconfinedTestDispatcher())
        // Initialize the ViewModel with the mocked use case
        viewModel = ProductViewModel(mockUseCase)
    }

    @After
    fun tearDown() {
        // Reset the main dispatcher after the tests
        Dispatchers.resetMain()
    }

    @Test
    fun `given successful response, when fetching products, then uiState is updated with products`() = runTest {
        // Arrange (Prepare test data)
        val products = listOf(
            Product(
                id = "1",
                headline = "Samsung Galaxy S21",
                price = 799.99,
                discountedPrice = 699.99,
                images = listOf("url1"),
                rating = 4.5,
                reviewsCount = 120
            )
        )

        // Simulate the behavior of the execute() method in the mocked use case
        whenever(mockUseCase.execute("samsung")).thenReturn(products)

        // Act (Execute the code to be tested)
        viewModel.fetchProducts("samsung")

        // Assert (Verify the results)
        val uiState = viewModel.uiState.value
        assertEquals(1, uiState.products.size) // Verify that the product list contains one item
        assertEquals("Samsung Galaxy S21", uiState.products[0].headline) // Verify the product name
        assertNull(uiState.error) // Verify that there is no error in the UI state
    }

    @Test
    fun `given failure response, when fetching products, then uiState is updated with error`() = runTest {
        // Arrange (Prepare for an error scenario)
        val errorMessage = "Network Error"
        whenever(mockUseCase.execute("samsung")).thenThrow(RuntimeException(errorMessage))

        // Act
        viewModel.fetchProducts("samsung")

        // Assert
        val uiState = viewModel.uiState.value
        assertNull(uiState.products) // Verify that the product list is empty
        assertEquals(errorMessage, uiState.error) // Verify that the error matches the simulated one
    }
}
