package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.room.*
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {

//    @Transaction
//    @Query("Select * FROM movies where type = 'movie' order by createdAt asc")
//    suspend fun getFavoriteMovies(): List<MovieUiAndFavorite>
//
//    @Transaction
//    @Query("Select * FROM movies where type = 'tvshow' order by createdAt asc")
//    suspend fun getFavoriteTvShows(): List<MovieUiAndFavorite>
 @Transaction
    @Query("Select * FROM favorite where movieType = 'movie' order by created_at asc")
    suspend fun getFavoriteMovies(): List<FavoriteAndMovie>

    @Transaction
    @Query("Select * FROM favorite where movieType = 'tvshow' order by created_at asc")
    suspend fun getFavoriteTvShows(): List<FavoriteAndMovie>

    @Transaction
    @Query("Select * From favorite where movieId = :movieId limit 1")
    fun getFavoriteMovieById(movieId: Int): Flow<FavoriteAndMovie>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite): Long

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}