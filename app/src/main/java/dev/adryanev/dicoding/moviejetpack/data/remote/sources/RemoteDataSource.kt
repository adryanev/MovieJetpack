package dev.adryanev.dicoding.moviejetpack.data.remote.sources

import dev.adryanev.dicoding.moviejetpack.data.entities.DataResult
import retrofit2.Response

interface RemoteDataSource<T : Any> {
    suspend fun getResult(call: suspend () -> Response<T>): DataResult<T>
}