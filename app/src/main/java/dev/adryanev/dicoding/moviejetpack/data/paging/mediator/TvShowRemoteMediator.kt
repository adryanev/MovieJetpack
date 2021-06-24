package dev.adryanev.dicoding.moviejetpack.data.paging.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.adryanev.dicoding.moviejetpack.data.constants.Constants
import dev.adryanev.dicoding.moviejetpack.data.entities.DataResult
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieRemoteKey
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.local.AppDatabase
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.mapper.toMovieUi
import dev.adryanev.dicoding.moviejetpack.data.remote.TvShowRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*
import javax.inject.Inject

@ExperimentalPagingApi
class TvShowRemoteMediator @Inject constructor(
    private val service: TvShowRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val db: AppDatabase
) : RemoteMediator<Int, MovieUi>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, MovieUi>): MediatorResult {

        Timber.d("load type: $loadType")
        val loadKey = when (loadType) {
            LoadType.REFRESH -> {
                Constants.FIRST_PAGE
            }
            LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
            LoadType.APPEND -> {
                val remoteKey = getRemoteKeyFromLastItem(state)
                if (remoteKey?.nextKey == null) {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                remoteKey.nextKey
            }
        }

        when (val apiResult = service.getTvShowList(page = loadKey)) {
            is DataResult.Success -> {
                val moviesFromNetwork = apiResult.data.results!!
                val endOfPaginationReached = moviesFromNetwork.isEmpty()
                db.withTransaction {
                    //Invalidate local cache if we are resubmitting paging
                    if (loadType == LoadType.REFRESH) {
                        localDataSource.clearAllTable(TvShow.TYPE)
                    }
                    insertNewPageData(moviesFromNetwork, endOfPaginationReached, loadKey)

                }
                return MediatorResult.Success(
                    endOfPaginationReached = endOfPaginationReached
                )
            }
            is DataResult.Error -> {
                return MediatorResult.Error(
                    apiResult.e
                )
            }
        }

    }

    private suspend fun insertNewPageData(
        movieFromNetwork: List<TvShow>?,
        endOfPageReached: Boolean,
        page: Int
    ) {
        val nextKey = if (endOfPageReached) null else page + 1
        val key = movieFromNetwork?.map {
            MovieRemoteKey(
                repoId = it.id,
                nextKey = nextKey,
                type = TvShow.TYPE,
                createdAt = Date()
            )
        }
        if (key != null) {
            localDataSource.insertAllMovieKeys(key)
        }
        if (movieFromNetwork != null) {
            localDataSource.insertAllMovies(movieFromNetwork.map { it.toMovieUi() })
        }

    }

    private suspend fun getRemoteKeyFromLastItem(state: PagingState<Int, MovieUi>): MovieRemoteKey? =
        withContext(Dispatchers.IO) {
            state.lastItemOrNull()?.let {
                it.id?.let { it1 -> localDataSource.findMovieRemoteKeyById(it1) }
            }
        }


}