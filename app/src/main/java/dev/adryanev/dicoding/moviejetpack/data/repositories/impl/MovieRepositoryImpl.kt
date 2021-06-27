package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.local.AppDatabase
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.paging.mediator.MovieRemoteMediator
import dev.adryanev.dicoding.moviejetpack.data.remote.sources.MovieRemoteDataSource
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val database: AppDatabase,
    private val pageConfig: PagingConfig
) : MovieRepository {

    @ExperimentalPagingApi
    override suspend fun getMovieList(): Flow<PagingData<MovieUi>> {
        val localPagingSourceFactory = { localDataSource.getAllMovies() }
        val remoteMediator =
            MovieRemoteMediator(remoteDataSource, localDataSource, database)
        return Pager(
            pageConfig,
            pagingSourceFactory = localPagingSourceFactory,
            remoteMediator = remoteMediator
        ).flow
    }


    override suspend fun getMovieById(id: Int): Flow<MovieUi> = withContext(Dispatchers.IO) {
        localDataSource.findMovieById(id)
    }


}