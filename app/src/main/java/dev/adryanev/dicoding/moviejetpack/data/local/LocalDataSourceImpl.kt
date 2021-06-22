package dev.adryanev.dicoding.moviejetpack.data.local

import androidx.paging.PagingSource
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieRemoteKey
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShowRemoteKey
import dev.adryanev.dicoding.moviejetpack.data.local.dao.MovieDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.MovieRemoteKeyDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.TvShowDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.TvShowRemoteKeyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val tvShowDao: TvShowDao,
    private val movieRemoteKeyDao: MovieRemoteKeyDao,
    private val tvRemoteKeyDao: TvShowRemoteKeyDao
) : LocalDataSource {
    override fun getAllMovies(): PagingSource<Int, Movie> = movieDao.getAllMovie()

    override fun getAllTvShows(): PagingSource<Int, TvShow> = tvShowDao.getAllTvShow()

    override fun findMovieRemoteKeyById(id: Int): MovieRemoteKey? =
        movieRemoteKeyDao.getRemoteKeyById(id)

    override fun findTvRemoteKeyById(id: Int): TvShowRemoteKey? =
        tvRemoteKeyDao.getRemoteKeyById(id)

    override fun findMovieById(id: Int): Flow<Movie> = movieDao.getMovie(id)

    override fun findTvShowById(id: Int): Flow<TvShow> = tvShowDao.getTvShow(id)

    override suspend fun insertAllMovieKeys(list: List<MovieRemoteKey>) =
        movieRemoteKeyDao.insertAll(list)

    override suspend fun insertAllTvKeys(list: List<TvShowRemoteKey>) =
        tvRemoteKeyDao.insertAll(list)

    override suspend fun insertAllMovies(list: List<Movie>) = movieDao.insert(list)

    override suspend fun insertAllTvShow(list: List<TvShow>) = tvShowDao.insert(list)

    override suspend fun clearAllMoviesTable() {
        movieDao.deleteAll()
        movieRemoteKeyDao.clearRemoteKeys()
    }
    override suspend fun clearAllTvTable() {
        tvShowDao.deleteAll()
        tvRemoteKeyDao.clearRemoteKeys()
    }
}