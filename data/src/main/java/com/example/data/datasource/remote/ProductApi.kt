package com.example.data.datasource.remote


/**
 * Created by Safa NAOUI on 20,January,2025
 */

import com.example.data.datasource.remote.dto.ProductDetailsResponse
import com.example.data.datasource.remote.dto.ProductResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Retrofit API interface for fetching product data from the server.
 */

interface ProductApi {
    @GET("search")
    suspend fun searchProducts(@Query("keyword") keyword: String): ProductResponse

    @GET("details")
    suspend fun getProductDetails(@Query("id") id: String): ProductDetailsResponse
}
