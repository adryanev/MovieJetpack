package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import dev.adryanev.dicoding.moviejetpack.data.remote.MovieRemoteDataSource
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies.ResponseListMovie
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.utils.networkOnlyResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource,
) : MovieRepository {
//    override suspend fun getMovieList() = networkBoundResource(
//        query = {movieDao.getAllMovie()},
//        fetch = {remoteDataSource.getMovieList()},
//        saveFetchResult = {items ->
//            val result= items.data?.results
//            Timber.d(result.toString())
//            movieDao.insert(result)
//        }
//    )

    override suspend fun getMovieList(): Flow<Resource<ResponseListMovie>> = networkOnlyResource(
        fetch = { remoteDataSource.getMovieList() },
        processResult = { it.data!! }
    )

}