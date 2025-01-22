package com.example.data.datasource.local.database

/**
 * Created by Safa NAOUI on 20,January,2025
 */

import com.example.data.datasource.local.dao.ProductDao
import com.example.data.datasource.local.entity.ProductEntity

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.datasource.local.dao.ProductDetailDao
import com.example.data.utils.Converters

/**
 * The main database class for the application, using Room as the database framework.
 * Defines the database schema, entities, and provides DAO access.
 *
 * @property productDao Provides access to product-related database operations.
 * @property productDetailDao Provides access to product detail-related database operations.
 */

@Database(
    entities = [ProductEntity::class],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
    abstract fun productDetailDao(): ProductDetailDao
}