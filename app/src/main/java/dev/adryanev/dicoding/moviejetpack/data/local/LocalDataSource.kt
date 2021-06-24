package dev.adryanev.dicoding.moviejetpack.data.local

import androidx.paging.PagingSource
import dev.adryanev.dicoding.moviejetpack.data.entities.*
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllMovies(): PagingSource<Int, MovieUi>
    fun getAllTvShows(): PagingSource<Int, MovieUi>
    fun findMovieRemoteKeyById(id:Int): MovieRemoteKey?
    fun findMovieById(id:Int): Flow<MovieUi>
    fun findTvShowById(id: Int): Flow<MovieUi>
    fun getAllFavoriteMovie(): List<MovieUiAndFavorite>
    fun getAllFavoriteTvShow(): List<MovieUiAndFavorite>
    suspend fun insertAllMovieKeys(list: List<MovieRemoteKey>)
    suspend fun insertAllMovies(list: List<MovieUi>)
    suspend fun clearAllTable(type: String)
}