package dev.adryanev.dicoding.moviejetpack.utils

import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline onFetchFailed: (Throwable) -> Unit = { },
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) =

    flow {
        emit(Resource.loading())
        val data = query().first()

        val flow = if (shouldFetch(data)) {
            emit(Resource.loading(data))
            try {
                saveFetchResult(fetch())
                query().map { Resource.success(it) }
            } catch (throwable: Throwable) {
                onFetchFailed(throwable)
                query().map { Resource.error(throwable.message!!, it) }
            }
        } else {
            query().map { Resource.success(it) }
        }

        emitAll(flow)
    }.flowOn(Dispatchers.IO)