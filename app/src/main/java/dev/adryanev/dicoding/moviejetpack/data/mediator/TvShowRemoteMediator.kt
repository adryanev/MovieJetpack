package dev.adryanev.dicoding.moviejetpack.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.adryanev.dicoding.moviejetpack.data.constants.Constants
import dev.adryanev.dicoding.moviejetpack.data.entities.DataResult
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShowRemoteKey
import dev.adryanev.dicoding.moviejetpack.data.local.AppDatabase
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.remote.TvShowRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ExperimentalPagingApi
class TvShowRemoteMediator @Inject constructor(
    private val service: TvShowRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val db: AppDatabase
) : RemoteMediator<Int, TvShow>() {

    override suspend fun load(loadType: LoadType, state: PagingState<Int, TvShow>): MediatorResult {

        if (loadType == LoadType.PREPEND) {
            return MediatorResult.Success(endOfPaginationReached = true)
        }

        val page = computePage(loadType, state)

        when (val apiResult = service.getTvShowList(page = page)) {
            is DataResult.Success -> {
                val moviesFromNetwork = apiResult.data.results
                val endOfPaginationReached = moviesFromNetwork?.isEmpty()
                db.withTransaction {
                    //Invalidate local cache if we are resubmitting paging
                    if (loadType == LoadType.REFRESH) {
                        localDataSource.clearAllMoviesTable()
                    }
                    insertNewPageData(moviesFromNetwork, endOfPaginationReached!!, page)

                }
                return MediatorResult.Success(
                    endOfPaginationReached = endOfPaginationReached!!
                )
            }
            is DataResult.Error -> {
                return MediatorResult.Error(
                    apiResult.e
                )
            }
        }

        return MediatorResult.Error(Exception())
    }

    private suspend fun insertNewPageData(
        movieFromNetwork: List<TvShow>?,
        endOfPageReached: Boolean,
        page: Int
    ) {
        val nextKey = if (endOfPageReached) null else page + 1
        val prevKey = if (page == Constants.FIRST_PAGE) null else page - 1
        val key = movieFromNetwork?.map {
            TvShowRemoteKey(repoId = it.id!!, nextKey = nextKey, prevKey = prevKey)
        }

        if (movieFromNetwork != null) {
            localDataSource.insertAllTvShow(movieFromNetwork)
        }
        if (key != null) {
            localDataSource.insertAllTvKeys(key)
        }
    }

    private suspend fun computePage(loadType: LoadType, state: PagingState<Int, TvShow>): Int =
        when (loadType) {
            LoadType.REFRESH -> {
                val remoteKey = getRemoteKeyFromCurrentPosition(state)
                remoteKey?.nextKey?.minus(1) ?: Constants.FIRST_PAGE
            }
            LoadType.APPEND -> {
                getRemoteKeyFromLastItem(state)?.nextKey
            }
            else -> null
        }
            ?: throw Exception("Problem within transactions, Page cannot be found.")

    private suspend fun getRemoteKeyFromLastItem(state: PagingState<Int, TvShow>): TvShowRemoteKey? =
        withContext(Dispatchers.IO) {
            state.lastItemOrNull()?.let {
                localDataSource.findTvRemoteKeyById(it.id!!)
            }
        }

    private suspend fun getRemoteKeyFromCurrentPosition(state: PagingState<Int, TvShow>): TvShowRemoteKey? =
        withContext(Dispatchers.IO) {
            state.anchorPosition?.let { position ->
                state.closestItemToPosition(position)?.id?.let { id ->
                    localDataSource.findTvRemoteKeyById(id)
                }

            }
        }

}