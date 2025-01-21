package com.example.data.repository


import com.example.data.datasource.LocalDataSource
import com.example.data.datasource.RemoteDataSource
import com.example.domain.model.Product
import com.example.domain.model.ProductDetail
import com.example.domain.repository.ProductRepository
import com.example.domain.utils.DataResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Implementation of the `ProductRepository` interface that manages data fetching
 * from both local and remote data sources. It follows the Repository pattern to
 * ensure separation of concerns and improve maintainability.
 */

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ProductRepository {

    /**
     * Searches for products using the provided keyword. It first checks the local database;
     * if no results are found, it fetches data from the remote API and saves it locally
     * for future use.
     */
    override fun searchProducts(keyword: String): Flow<DataResult<List<Product>>> = flow {
        emit(DataResult.Loading)
        try {
            val localResults = localDataSource.searchProducts(keyword)
            if (localResults.isNotEmpty()) {
                emit(DataResult.Success(localResults))
            } else {
                val remoteResults = remoteDataSource.searchProducts(keyword)
                emit(DataResult.Success(remoteResults))
                // Save the results locally for future searches
            }
        } catch (e: Exception) {
            emit(DataResult.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

    /**
     * Retrieves product details using the given product ID.
     * It first tries to fetch the details from the local database.
     * If not found, it then attempts to fetch them from the remote API.
     */
    override fun getProductDetails(id: String): Flow<DataResult<ProductDetail>> = flow {
        emit(DataResult.Loading)
        try {
            val localDetails = localDataSource.getProductDetails(id)
            emit(DataResult.Success(localDetails))
        } catch (e: Exception) {
            try {
                val remoteDetails = remoteDataSource.getProductDetails(id)
                emit(DataResult.Success(remoteDetails))
            } catch (networkError: Exception) {
                emit(DataResult.Error(networkError.localizedMessage ?: "Unknown Error"))
            }
        }
    }
}