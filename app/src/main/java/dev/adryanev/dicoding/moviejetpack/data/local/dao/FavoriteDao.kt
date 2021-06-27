package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Transaction
    @Query("Select * FROM favorite where movieType = 'movie' order by created_at asc")
    fun getFavoriteMovies(): PagingSource<Int, FavoriteAndMovie>

    @Transaction
    @Query("Select * FROM favorite where movieType = 'tvshow' order by created_at asc")
    fun getFavoriteTvShows(): PagingSource<Int, FavoriteAndMovie>

    @Transaction
    @Query("Select * From favorite where movieId = :movieId limit 1")
    fun getFavoriteMovieById(movieId: Int): Flow<FavoriteAndMovie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite): Long

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}