package dev.adryanev.dicoding.moviejetpack.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.adryanev.dicoding.moviejetpack.data.constants.Constants
import dev.adryanev.dicoding.moviejetpack.data.entities.DataResult
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieRemoteKey
import dev.adryanev.dicoding.moviejetpack.data.local.AppDatabase
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.remote.MovieRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

@ExperimentalPagingApi
class MovieRemoteMediator @Inject constructor(
    private val service: MovieRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val db: AppDatabase
) : RemoteMediator<Int, Movie>() {
    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
        return try {
            val loadKey = when (loadType) {
                LoadType.REFRESH -> {
                    Timber.d("refreshing")
                    1
//                    val remoteKeys = getRemoteKeyFromCurrentPosition(state)
//                    //Return current page if exists, else return the default page: 1
//                    remoteKeys?.nextKey?.minus(1) ?: Constants.FIRST_PAGE
                }
                LoadType.PREPEND -> {
                    Timber.d("Prepend")
                    return MediatorResult.Success(endOfPaginationReached = true)}
                LoadType.APPEND -> {
                    Timber.d("Append")
                   val remoteKey= getRemoteKeyFromLastItem(state)?.nextKey
                    if (remoteKey == null) {
                        Timber.d("remoteKey= null, end of page")
                        MediatorResult.Success(endOfPaginationReached = true)
                    }

                    remoteKey

                }
            }
            when (val response = service.getMovieList(loadKey ?: 1)) {
                is DataResult.Success -> {
                    Timber.d("Success Loading Data from network")
                    val moviesFromNetwork = response.data.results
                    val endOfPaginationReached = moviesFromNetwork?.isEmpty()
                    Timber.d("endOfPagination = $endOfPaginationReached")

                    db.withTransaction {
                        //Invalidate local cache if we are resubmitting paging
                        if (loadType == LoadType.REFRESH) {
                            localDataSource.clearAllMoviesTable()
                        }
                        if (endOfPaginationReached != null) {
                            if (loadKey != null) {
                                insertNewPageData(
                                    moviesFromNetwork,
                                    endOfPaginationReached,
                                    loadKey
                                )
                            }
                        }

                    }
                    return MediatorResult.Success(
                        endOfPaginationReached = endOfPaginationReached!!
                    )
                }
                is DataResult.Error -> {
                    return MediatorResult.Error(
                        response.e
                    )
                }
            }

        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

//    override suspend fun load(loadType: LoadType, state: PagingState<Int, Movie>): MediatorResult {
//
//        if (loadType == LoadType.PREPEND) {
//            return MediatorResult.Success(endOfPaginationReached = true)
//        }
//
//        val page = computePage(loadType, state)
//
//        when (val apiResult = service.getMovieList(page = page)) {
//            is DataResult.Success -> {
//                val moviesFromNetwork = apiResult.data.results
//                val endOfPaginationReached = moviesFromNetwork?.isEmpty()
//                db.withTransaction {
//                    //Invalidate local cache if we are resubmitting paging
//                    if (loadType == LoadType.REFRESH) {
//                        localDataSource.clearAllTables()
//                    }
//                    insertNewPageData(moviesFromNetwork, endOfPaginationReached!!, page)
//
//                }
//                return MediatorResult.Success(
//                    endOfPaginationReached = endOfPaginationReached!!
//                )
//            }
//            is DataResult.Error -> {
//                return MediatorResult.Error(
//                    apiResult.e
//                )
//            }
//        }
//
//    }

    private suspend fun insertNewPageData(
        movieFromNetwork: List<Movie>?,
        endOfPageReached: Boolean,
        page: Int
    ) {
        val nextKey = if (endOfPageReached) null else page + 1
        val prevKey = if (page == Constants.FIRST_PAGE) null else page - 1
        val key = movieFromNetwork?.map {
            MovieRemoteKey(repoId = it.id!!, nextKey = nextKey, prevKey = prevKey)
        }

        if (movieFromNetwork != null) {
            localDataSource.insertAllMovies(movieFromNetwork)
        }
        if (key != null) {
            localDataSource.insertAllMovieKeys(key)
        }
    }

    private suspend fun computePage(loadType: LoadType, state: PagingState<Int, Movie>): Int =
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

    private suspend fun getRemoteKeyFromLastItem(state: PagingState<Int, Movie>): MovieRemoteKey? =
        withContext(Dispatchers.IO) {
            state.lastItemOrNull()?.let {
                localDataSource.findMovieRemoteKeyById(it.id!!)
            }
        }

    private suspend fun getRemoteKeyFromCurrentPosition(state: PagingState<Int, Movie>): MovieRemoteKey? =
        withContext(Dispatchers.IO) {
            state.anchorPosition?.let { position ->
                state.closestItemToPosition(position)?.id?.let { id ->
                    localDataSource.findMovieRemoteKeyById(id)
                }

            }
        }

}