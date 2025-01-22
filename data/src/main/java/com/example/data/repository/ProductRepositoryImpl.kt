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

class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ProductRepository {

    // Function to search for products using a keyword
    override fun searchProducts(keyword: String): Flow<DataResult<List<Product>>> = flow {
        emit(DataResult.Loading)
        try {
            // 1. Search for products locally first
            // 2. If local results exist, return them
            // 3. If no local results, fetch from the remote data source
            val localResults = localDataSource.searchProducts(keyword)
            if (localResults.isNotEmpty()) {
                emit(DataResult.Success(localResults))
            } else {
                val remoteResults = remoteDataSource.searchProducts(keyword)
                emit(DataResult.Success(remoteResults))
            }
        } catch (e: Exception) {
            emit(DataResult.Error(e.localizedMessage ?: "Unknown Error"))
        }
    }

    // Function to get product details by its ID
    override fun getProductDetails(id: String): Flow<DataResult<ProductDetail>> = flow {
        try {
            emit(DataResult.Loading) // Loading state

            // Fetching from the remote data source
            val remoteProductDetail = remoteDataSource.getProductDetails(id)

            // Saving locally
            // localDataSource.saveProductDetail(remoteProductDetail)

            // Emit the fetched data
            emit(DataResult.Success(remoteProductDetail))

        } catch (e: Exception) {
            emit(DataResult.Error(e.toString())) // Gestion des erreurs
        }
    }
}
