package com.example.data.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * This class provides TypeConverters for Room database to handle data types that are not natively supported.
 * Specifically, it converts a List<String> to a JSON string for storage and vice versa when retrieving data.
 * Room requires these converters to persist complex data structures like lists.
 */

class Converters {
    @TypeConverter
    fun fromStringList(value: List<String>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringList(value: String): List<String> {
        val listType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(value, listType)
    }
}
