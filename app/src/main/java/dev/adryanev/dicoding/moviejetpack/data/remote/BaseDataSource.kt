package dev.adryanev.dicoding.moviejetpack.data.remote

import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call: suspend ()-> Response<T>): Resource<T> {

        try {
            val response = call()
            if(response.isSuccessful){
                val body = response.body()
                if(body!=null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}")
        } catch (e: Exception){
            Timber.e(e)
            return error(e.message ?: e.toString())

        }
    }
    private fun <T> error(message: String): Resource<T> {
        Timber.e(message)
        return Resource.error("Network call has failed for a following reason: $message")
    }
}