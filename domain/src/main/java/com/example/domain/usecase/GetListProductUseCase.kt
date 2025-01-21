package com.example.domain.usecase

/**
 * Created by Safa NAOUI on 20,January,2025
 */
import com.example.domain.DataResult
import com.example.domain.model.Product
import kotlinx.coroutines.flow.Flow
import com.example.domain.repository.ProductRepository
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/** This class is a use case that allows displaying a list of products based on a search query. **/

class GetListProductUseCase @Inject constructor(
    private val repository: ProductRepository
) {

    fun execute(keyword: String): Flow<List<Product>> {
        return repository.searchProducts(keyword).map { result ->
            when (result) {
                is DataResult.Success -> result.data
                is DataResult.Error -> emptyList()
                is DataResult.Loading -> emptyList() // 🔹 Ajout du cas Loading
            }
        }
    }
}