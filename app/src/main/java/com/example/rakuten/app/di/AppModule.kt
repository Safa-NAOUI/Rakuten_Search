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

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
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
        ).build()
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