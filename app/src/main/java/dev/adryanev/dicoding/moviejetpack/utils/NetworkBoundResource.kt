package dev.adryanev.dicoding.moviejetpack.utils

import dev.adryanev.dicoding.moviejetpack.data.entities.Result
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
        emit(Result.loading())
        val data = query().first()

        val flow = if (shouldFetch(data)) {
            emit(Result.loading(data))
            try {
                saveFetchResult(fetch())
                query().map { Result.success(it) }
            } catch (throwable: Throwable) {
                onFetchFailed(throwable)
                query().map { Result.error(throwable.message!!, it) }
            }
        } else {
            query().map { Result.success(it) }
        }

        emitAll(flow)
    }.flowOn(Dispatchers.IO)