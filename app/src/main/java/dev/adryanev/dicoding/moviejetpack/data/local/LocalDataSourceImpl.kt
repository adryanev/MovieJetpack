package dev.adryanev.dicoding.moviejetpack.data.local

import androidx.paging.PagingSource
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieRemoteKey
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import dev.adryanev.dicoding.moviejetpack.data.local.dao.FavoriteDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.MovieDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.MovieRemoteKeyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val movieRemoteKeyDao: MovieRemoteKeyDao,
    private val favoriteDao: FavoriteDao
) : LocalDataSource {
    override fun getAllMovies(): PagingSource<Int, MovieUi> = movieDao.getAllMovie()

    override fun getAllTvShows(): PagingSource<Int, MovieUi> = movieDao.getAllTvShow()

    override fun findMovieRemoteKeyById(id: Int): MovieRemoteKey? =
        movieRemoteKeyDao.getRemoteKeyById(id)

    override fun findMovieById(id: Int): Flow<MovieUi> = movieDao.getMovie(id)

    override fun findTvShowById(id: Int): Flow<MovieUi> = movieDao.getTvShow(id)
    override suspend fun getAllFavoriteMovie(): List<MovieUiAndFavorite> {
        return favoriteDao.getFavoriteMovies()
    }

    override suspend fun getAllFavoriteTvShow(): List<MovieUiAndFavorite> {
        return favoriteDao.getFavoriteTvShows()
    }

    override suspend fun insertAllMovieKeys(list: List<MovieRemoteKey>) =
        movieRemoteKeyDao.insertAll(list)

    override suspend fun insertAllMovies(list: List<MovieUi>) = movieDao.insertMovie(list)


    override suspend fun clearAllTable(type: String) {
        movieDao.deleteAll(type)
        movieRemoteKeyDao.clearRemoteKeys(type)
    }

}