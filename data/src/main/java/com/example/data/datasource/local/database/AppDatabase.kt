package com.example.data.datasource.local.database

/**
 * Created by Safa NAOUI on 20,January,2025
 */

import com.example.data.datasource.local.dao.ProductDao
import com.example.data.datasource.local.entity.ProductEntity

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.data.datasource.local.entity.ImageUrlEntity
import com.example.data.datasource.local.entity.ProductDetailEntity
import com.example.data.utils.SellerConverter
import com.example.data.utils.Converters
import com.example.data.utils.ImageUrlConverter

/**
 * The main database class for the application, using Room as the database framework.
 * Defines the database schema, entities, and provides DAO access.
 **/

@Database(
    entities = [ProductEntity::class, ProductDetailEntity::class, ImageUrlEntity::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(Converters::class, SellerConverter::class, ImageUrlConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}