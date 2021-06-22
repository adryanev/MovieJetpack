package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.local.AppDatabase
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.local.dao.TvShowDao
import dev.adryanev.dicoding.moviejetpack.data.mediator.MovieRemoteMediator
import dev.adryanev.dicoding.moviejetpack.data.mediator.TvShowRemoteMediator
import dev.adryanev.dicoding.moviejetpack.data.remote.TvShowRemoteDataSource
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.utils.networkBoundResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: TvShowRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val database: AppDatabase,
    private val pageConfig: PagingConfig
) : TvShowRepository {

    @ExperimentalPagingApi
    override suspend fun getTvShowList() : Flow<PagingData<TvShow>>{
        val localPaging = {localDataSource.getAllTvShows()}
        val remoteMediator = TvShowRemoteMediator(remoteDataSource,localDataSource,database)
        return Pager(
            pageConfig,
            pagingSourceFactory = localPaging,
            remoteMediator = remoteMediator
        ).flow
    }

    override suspend fun getTvShowById(id: Int): Flow<TvShow> = withContext(Dispatchers.IO) {
        localDataSource.findTvShowById(id)
    }

}