//package com.example.data
//
//import com.example.domain.model.Product
//import kotlinx.coroutines.ExperimentalCoroutinesApi
//
//import com.example.domain.repository.ProductRepository
//import com.example.domain.usecase.GetListProductUseCase
//import com.example.domain.utils.DataResult
//import kotlinx.coroutines.flow.flowOf
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.Assert.assertEquals
//
//@ExperimentalCoroutinesApi
//class GetListProductUseCaseTest_searchProduct {
//
//    @get:Rule
//    val mainDispatcherRule = TestCoroutineRule() // Pour gérer les coroutines dans les tests
//
//    private lateinit var useCase: GetListProductUseCase
//
//    // Mock du repository avec Mockito
//    private val repository: ProductRepository = mock(ProductRepository::class.java)
//
//    @Before
//    fun setUp() {
//        useCase = GetListProductUseCase(repository)
//    }
//
//    @Test
//    fun `searchProduct should return products when repository returns success`() = runTest {
//        // GIVEN
//        val keyword = "phone"
//        val products = listOf(Product("1", "iPhone"), Product("2", "Samsung"))
//        val flow = flowOf(DataResult.Success(products))
//
//        `when`(repository.searchProducts(keyword)).thenReturn(flow)
//
//        // WHEN & THEN
//        useCase.searchProduct(keyword).test {
//            assertEquals(products, awaitItem()) // Vérifie si la liste retournée est correcte
//            awaitComplete()
//        }
//    }
//
//    @Test
//    fun `searchProduct should return empty list when repository returns error`() = runTest {
//        // GIVEN
//        val keyword = "laptop"
//        val flow = flowOf(DataResult.Error(Exception("Network error")))
//
//        `when`(repository.searchProducts(keyword)).thenReturn(flow)
//
//        // WHEN & THEN
//        useCase.searchProduct(keyword).test {
//            assertEquals(emptyList<Product>(), awaitItem()) // Vérifie que la liste est vide
//            awaitComplete()
//        }
//    }
//
//    @Test
//    fun `searchProduct should return empty list when repository returns loading`() = runTest {
//        // GIVEN
//        val keyword = "tablet"
//        val flow = flowOf(DataResult.Loading)
//
//        `when`(repository.searchProducts(keyword)).thenReturn(flow)
//
//        // WHEN & THEN
//        useCase.searchProduct(keyword).test {
//            assertEquals(emptyList<Product>(), awaitItem()) // Vérifie que la liste est vide
//            awaitComplete()
//        }
//    }
//}
