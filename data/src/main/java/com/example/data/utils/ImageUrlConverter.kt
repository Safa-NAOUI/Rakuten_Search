package com.example.data.utils
import androidx.room.TypeConverter
import com.example.data.datasource.local.entity.ImageUrlEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
class ImageUrlConverter {

    @TypeConverter
    fun fromImageUrlList(value: List<ImageUrlEntity>): String {
        val json = Gson().toJson(value) // Converts list to JSON
        return json
    }

    @TypeConverter
    fun toImageUrlList(value: String): List<ImageUrlEntity> {
        val listType = object : TypeToken<List<ImageUrlEntity>>() {}.type
        return Gson().fromJson(value, listType) // Converts JSON back to list
    }
}