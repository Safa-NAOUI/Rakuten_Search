package com.example.rakuten.usecase

import com.example.domain.repository.ProductRepository
import com.example.domain.usecase.GetListProductUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import app.cash.turbine.test
import com.example.domain.model.Product
import com.example.domain.utils.DataResult
import junit.framework.TestCase.assertEquals
import org.junit.Assert.assertTrue
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetListProductUseCaseTest {

    private lateinit var useCase: GetListProductUseCase

    @Mock
    private val repository: ProductRepository = mock()

    @Before
    fun setUp() {
        // init mocks
        MockitoAnnotations.openMocks(this)
        useCase = GetListProductUseCase(repository)
    }

    /** return products when repository returns a successful result **/
    @Test
    fun `searchProduct should return products when repository returns success`() = runTest {
        val keyword = "phone"
        val products = listOf(
            Product(
                id = "1",
                headline = "iPhone 13",
                newBestPrice = 999.99,
                usedBestPrice = 700.00,
                imagesUrls = listOf("url1", "url2"),
                description = "Le dernier modèle d'iPhone.",
                score = 4.5f,
                nbReviews = 2500
            ),
            Product(
                id = "2",
                headline = "Samsung Galaxy S21",
                newBestPrice = 899.99,
                usedBestPrice = 600.00,
                imagesUrls = listOf("url3", "url4"),
                description = "Un modèle phare de Samsung.",
                score = 4.7f,
                nbReviews = 1800
            )
        )

        val flow = flowOf(DataResult.Success(products))
        whenever(repository.searchProducts(keyword)).thenReturn(flow)

        useCase.searchProduct(keyword).test {
            val result = awaitItem()
            assertTrue(result is List<*>)
            assertEquals(products, result)
            awaitComplete()
        }
    }

    /**  return an empty list when the repository returns an error **/
    @Test
    fun `searchProduct should return empty list when repository returns error`() = runTest {
        val keyword = "phone"
        val errorMessage = "Error fetching products"
        val flow = flowOf(DataResult.Error(errorMessage))

        whenever(repository.searchProducts(keyword)).thenReturn(flow)

        useCase.searchProduct(keyword).test {
            val result = awaitItem()
            assertTrue(result is List<*>)
            assertEquals(emptyList<Product>(), result)
            awaitComplete()
        }
    }

    /**  return an empty list when repository is in a loading state **/
    @Test
    fun `searchProduct should return empty list when repository is loading`() = runTest {
        val keyword = "phone"

        // Returning DataResult.Success with an empty list (indicating loading)
        val flow = flowOf(DataResult.Success(emptyList<Product>()))

        whenever(repository.searchProducts(keyword)).thenReturn(flow)

        useCase.searchProduct(keyword).test {
            val result = awaitItem()
            assertTrue(result is List<*>)
            assertEquals(emptyList<Product>(), result)
            awaitComplete()
        }
    }
}
