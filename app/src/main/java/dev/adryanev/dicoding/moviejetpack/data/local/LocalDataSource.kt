package dev.adryanev.dicoding.moviejetpack.data.local

import androidx.paging.PagingSource
import dev.adryanev.dicoding.moviejetpack.data.entities.*
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    fun getAllMovies(): PagingSource<Int, Movie>
    fun getAllTvShows(): PagingSource<Int, TvShow>
    fun findMovieRemoteKeyById(id:Int): MovieRemoteKey?
    fun findTvRemoteKeyById(id:Int): TvShowRemoteKey?
    fun findMovieById(id:Int): Flow<Movie>
    fun findTvShowById(id: Int): Flow<TvShow>
    suspend fun insertAllMovieKeys(list: List<MovieRemoteKey>)
    suspend fun insertAllTvKeys(list: List<TvShowRemoteKey>)
    suspend fun insertAllMovies(list: List<Movie>)
    suspend fun insertAllTvShow(list: List<TvShow>)
    suspend fun clearAllMoviesTable()
    suspend fun clearAllTvTable()
}