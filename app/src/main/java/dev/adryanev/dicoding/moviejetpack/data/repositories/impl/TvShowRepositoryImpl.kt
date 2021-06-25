package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.local.AppDatabase
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.paging.datasource.MoviePagingDataSource
import dev.adryanev.dicoding.moviejetpack.data.paging.datasource.TvShowPagingDataSource
import dev.adryanev.dicoding.moviejetpack.data.paging.mediator.MovieRemoteMediator
import dev.adryanev.dicoding.moviejetpack.data.paging.mediator.TvShowRemoteMediator
import dev.adryanev.dicoding.moviejetpack.data.remote.TvShowRemoteDataSource
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: TvShowRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val database: AppDatabase,
    private val pageConfig: PagingConfig
) : TvShowRepository {


    @ExperimentalPagingApi
    override suspend fun getTvShowList():  Flow<PagingData<MovieUi>> {
        val localPagingSourceFactory = { localDataSource.getAllTvShows() }
        val remoteMediator =
            TvShowRemoteMediator(remoteDataSource, localDataSource, database)
        return Pager(
            pageConfig,
            pagingSourceFactory = localPagingSourceFactory,
            remoteMediator = remoteMediator
        ).flow
    }


//    @ExperimentalPagingApi
//    override suspend fun getTvShowList(): Flow<PagingData<MovieUi>> =
//        Pager(
//            pageConfig,
//        ) { TvShowPagingDataSource(remoteDataSource) }.flow

    override suspend fun getTvShowById(id: Int): Flow<MovieUi> = withContext(Dispatchers.IO) {
        localDataSource.findTvShowById(id)
    }

}