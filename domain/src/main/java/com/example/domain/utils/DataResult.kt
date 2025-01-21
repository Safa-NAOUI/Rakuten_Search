package com.example.domain.utils

sealed class DataResult<out T> {
    data class Success<out T>(val data: T) : DataResult<T>()
    data class Error(val message: String) : DataResult<Nothing>()
    data object Loading : DataResult<Nothing>()
}