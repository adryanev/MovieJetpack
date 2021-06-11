package dev.adryanev.dicoding.moviejetpack.utils

import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

inline fun <T, A> networkOnlyResource(
    crossinline fetch: suspend () -> A, // Resource<ResponseListMovie>
    crossinline onFetchFailed: (Throwable) -> Unit = { },  // error handler
    crossinline processResult: (A) -> T

) = flow<Resource<T>> {
    emit(Resource.loading())
    try {
        emit(Resource.success(processResult(fetch())))

    } catch (throwable: Throwable) {
        onFetchFailed(throwable)
        emit(Resource.error(throwable.message!!))
    }
}.flowOn(Dispatchers.IO)