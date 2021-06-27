package dev.adryanev.dicoding.moviejetpack.data.entities

sealed class DataResult<out T : Any> {
    data class Success<out T : Any>(val data: T) : DataResult<T>()
    data class Error(val e: Exception) : DataResult<Nothing>()

}
