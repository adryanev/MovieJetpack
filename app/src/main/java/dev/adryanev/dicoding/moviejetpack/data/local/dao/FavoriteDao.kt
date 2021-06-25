package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.room.*
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

    @Transaction
    @Query("Select * FROM favorite where type = 'movie' order by id asc")
    suspend fun getFavoriteMovies(): List<MovieUiAndFavorite>

    @Transaction
    @Query("Select * FROM favorite where type = 'tvshow' order by id asc")
    suspend fun getFavoriteTvShows(): List<MovieUiAndFavorite>

    @Transaction
    @Query("Select * From favorite where type = 'movie' and id = :movieId limit 1")
    fun getFavoriteMovieById(movieId: Int): Flow<MovieUiAndFavorite>

    @Transaction
    @Query("Select * From favorite where type = 'tvshow' and id = :movieId limit 1")
    fun getFavoriteTvShowById(movieId: Int): Flow<MovieUiAndFavorite>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite): Long

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}