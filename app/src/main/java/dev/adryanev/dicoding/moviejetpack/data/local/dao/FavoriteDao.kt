package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.room.*
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite

@Dao
interface FavoriteDao {

    @Transaction
    @Query("Select * FROM favorite where type = :type")
    fun getFavoriteMovies(type: String = Movie.TYPE): List<MovieUiAndFavorite>

    @Transaction
    @Query("Select * FROM favorite where type = :type")
    fun getFavoriteTvShows(type: String = TvShow.TYPE): List<MovieUiAndFavorite>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)
}