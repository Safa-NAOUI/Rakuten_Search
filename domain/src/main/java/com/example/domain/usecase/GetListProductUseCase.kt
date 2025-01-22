package com.example.domain.usecase


/**
 * Created by Safa NAOUI on 20,January,2025
 */
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import kotlinx.coroutines.flow.Flow
import com.example.domain.repository.ProductRepository
import com.example.domain.utils.DataResult
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetListProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    /** Function to search for products based on the keyword **/
    fun searchProduct(keyword: String): Flow<List<Product>> {
        return repository.searchProducts(keyword).map { result ->
            when (result) {
                is DataResult.Success -> result.data
                is DataResult.Error -> emptyList()
                is DataResult.Loading -> emptyList() // 🔹 Ajout du cas Loading
            }
        }
    }

    /** Function to get product details by its ID **/
    fun getProductDetails(productId: String): Flow<ProductDetail?> {
        return repository.getProductDetails(productId).map { result ->
            when (result) {
                is DataResult.Success -> result.data
                is DataResult.Error -> null
                is DataResult.Loading -> null // 🔹 Gérer le cas de chargement
            }
        }
    }
}
