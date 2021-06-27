package dev.adryanev.dicoding.moviejetpack.utils

import dev.adryanev.dicoding.moviejetpack.data.entities.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

inline fun <T, A> networkOnlyResource(
    crossinline fetch: suspend () -> A, // Result<ResponseListMovie>
    crossinline onFetchFailed: (Throwable) -> Unit = { },  // error handler
    crossinline processResult: (A) -> T

) = flow<Result<T>> {
    emit(Result.loading())
    try {
        emit(Result.success(processResult(fetch())))

    } catch (throwable: Throwable) {
        onFetchFailed(throwable)
        emit(Result.error(throwable.message!!))
    }
}.flowOn(Dispatchers.IO)