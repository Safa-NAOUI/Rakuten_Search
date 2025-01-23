package com.example.rakuten.usecase

import com.example.domain.repository.ProductRepository
import com.example.domain.usecase.GetListProductUseCase
import com.example.domain.utils.DataResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import com.example.domain.model.GlobalRating
import com.example.domain.model.ImageUrl
import com.example.domain.model.ProductDetail
import com.example.domain.model.Seller
import org.junit.Assert.assertNull
import org.mockito.Mock
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class GetProductDetailsUseCaseTest {

    private lateinit var useCase: GetListProductUseCase

    @Mock
    private val repository: ProductRepository = mock()

    @Before
    fun setUp() {
        // init mocks
        MockitoAnnotations.openMocks(this)
        useCase = GetListProductUseCase(repository)
    }

    /** return product details when repository returns a successful result **/
    @Test
    fun `getProductDetails should return product details when repository returns success`() =
        runTest {
            val productId = "1"
            val productDetail = ProductDetail(
                productId = 1L,
                headline = "iPhone 13",
                description = "Le dernier modèle d'iPhone.",
                salePrice = 999.99,
                newBestPrice = 999.99,
                usedBestPrice = 700.00,
                quality = "Excellent",
                type = "Smartphone",
                sellerComment = "A brand new iPhone.",
                categories = listOf("Electronics", "Smartphones"),
                globalRating = GlobalRating(score = 4.5, nbReviews = 2500),
                seller = Seller(id = 1, login = "Apple Store"),
                images = listOf(ImageUrl(size = "large", url = "url1"))
            )

            val flow = flowOf(DataResult.Success(productDetail))
            whenever(repository.getProductDetails(productId)).thenReturn(flow)

            useCase.getProductDetails(productId).test {
                val result = awaitItem()
                assertEquals(productDetail, result)
                awaitComplete()
            }
        }

    /** return null when repository returns an error **/
    @Test
    fun `getProductDetails should return null when repository returns error`() = runTest {
        val productId = "1"
        val errorMessage = "Error fetching product details"
        val flow = flowOf(DataResult.Error(errorMessage))

        whenever(repository.getProductDetails(productId)).thenReturn(flow)

        useCase.getProductDetails(productId).test {
            val result = awaitItem()
            assertNull(result)
            awaitComplete()
        }
    }

    /** return null when repository is in a loading state **/
    @Test
    fun `getProductDetails should return null when repository is loading`() = runTest {
        val productId = "1"
        val flow = flowOf(DataResult.Loading)

        whenever(repository.getProductDetails(productId)).thenReturn(flow)

        useCase.getProductDetails(productId).test {
            val result = awaitItem()
            assertNull(result)
            awaitComplete()
        }
    }
}