package dev.adryanev.dicoding.moviejetpack.data.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import dev.adryanev.dicoding.moviejetpack.data.constants.Constants
import dev.adryanev.dicoding.moviejetpack.data.entities.*
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


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Movie>
    ): MediatorResult {

        Timber.d("load type: $loadType")
       val loadKey = when(loadType){
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

        when (val apiResult = service.getMovieList(page = loadKey)) {
            is DataResult.Success -> {
                val moviesFromNetwork = apiResult.data.results!!
                val endOfPaginationReached = moviesFromNetwork.isEmpty()
                db.withTransaction {
                    //Invalidate local cache if we are resubmitting paging
                    if (loadType == LoadType.REFRESH) {
                        localDataSource.clearAllMoviesTable()
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
        movieFromNetwork: List<Movie>?,
        endOfPageReached: Boolean,
        page: Int
    ) {
        val nextKey = if (endOfPageReached) null else page + 1
        val key = movieFromNetwork?.map {
            MovieRemoteKey(repoId = it.id!!, nextKey = nextKey)
        }
        if (key != null) {
            localDataSource.insertAllMovieKeys(key)
        }
        if (movieFromNetwork != null) {
            localDataSource.insertAllMovies(movieFromNetwork)
        }

    }

    private suspend fun getRemoteKeyFromLastItem(state: PagingState<Int, Movie>): MovieRemoteKey? =
        withContext(Dispatchers.IO) {
            state.lastItemOrNull()?.let {
                localDataSource.findMovieRemoteKeyById(it.id!!)
            }
        }

}