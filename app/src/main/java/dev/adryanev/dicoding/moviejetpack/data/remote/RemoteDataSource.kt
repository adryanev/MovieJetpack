package dev.adryanev.dicoding.moviejetpack.data.remote

import dev.adryanev.dicoding.moviejetpack.data.entities.DataResult
import dev.adryanev.dicoding.moviejetpack.data.entities.Result
import retrofit2.Response

interface RemoteDataSource<T: Any> {
    suspend fun getResult(call: suspend() -> Response<T>) : DataResult<T>
}