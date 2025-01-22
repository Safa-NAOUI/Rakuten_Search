package com.example.rakuten.app.di

import android.content.Context
import androidx.room.Room
import com.example.data.datasource.local.LocalProductDataSource
import com.example.data.datasource.local.dao.ProductDao
import com.example.data.datasource.local.database.AppDatabase
import com.example.data.datasource.remote.ProductApi
import com.example.data.datasource.remote.RemoteProductDataSource
import com.example.data.repository.ProductRepositoryImpl
import com.example.domain.repository.ProductRepository
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.example.rakuten.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        val logging = HttpLoggingInterceptor().apply {
            level =
                HttpLoggingInterceptor.Level.BODY // Affiche le corps de la requête et de la réponse
        }
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            // .client(okHttpClient) // for logging
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ProductApi::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "product_db"
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideProductDao(database: AppDatabase): ProductDao {
        return database.productDao()
    }

    @Provides
    @Singleton
    fun provideProductRepository(
        remoteProductDataSource: RemoteProductDataSource,
        localProductDataSource: LocalProductDataSource
    ): ProductRepository {
        return ProductRepositoryImpl(remoteProductDataSource, localProductDataSource)
    }
}