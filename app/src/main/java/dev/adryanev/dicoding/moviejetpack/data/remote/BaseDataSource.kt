package dev.adryanev.dicoding.moviejetpack.data.remote

import dev.adryanev.dicoding.moviejetpack.data.entities.DataResult
import dev.adryanev.dicoding.moviejetpack.data.entities.Result
import dev.adryanev.dicoding.moviejetpack.utils.EspressoIdlingResource
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource<T: Any> : RemoteDataSource<T>{
    override suspend fun  getResult(call: suspend () -> Response<T>): DataResult<T> {

        try {
            EspressoIdlingResource.increment()
            val response = call.invoke()
            if (response.isSuccessful) {
                EspressoIdlingResource.decrement()
                val body = response.body()
                if (body != null) return DataResult.Success(body)
            }
            EspressoIdlingResource.decrement()
            return DataResult.Error(RuntimeException(response.errorBody()?.toString() ?: response.message()))
        } catch (e: Exception) {
            Timber.e(e)
            EspressoIdlingResource.decrement()
            return DataResult.Error(e)

        }
    }
}