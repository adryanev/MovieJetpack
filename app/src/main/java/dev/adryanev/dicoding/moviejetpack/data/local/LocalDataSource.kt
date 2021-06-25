package dev.adryanev.dicoding.moviejetpack.data.local

import androidx.paging.PagingSource
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieRemoteKey
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllMovies(): PagingSource<Int, MovieUi>
    fun getAllTvShows(): PagingSource<Int, MovieUi>
    fun findMovieRemoteKeyById(id: Int): MovieRemoteKey?
    fun findMovieById(id: Int): Flow<MovieUi>
    fun findTvShowById(id: Int): Flow<MovieUi>
    fun getAllFavoriteMovie(): PagingSource<Int,FavoriteAndMovie>
    fun getAllFavoriteTvShow(): PagingSource<Int,FavoriteAndMovie>
    suspend fun insertAllMovieKeys(list: List<MovieRemoteKey>)
    suspend fun insertAllMovies(list: List<MovieUi>)
    suspend fun clearAllTable(type: String)
    suspend fun setMovieFavorite(movieUi: FavoriteAndMovie, state: Boolean)
    fun getFavoriteMovieById(id: Int) : Flow<FavoriteAndMovie>
}