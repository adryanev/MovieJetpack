package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies where type = :type order by createdAt asc")
    fun getAllMovie(type: String = Movie.TYPE): PagingSource<Int, MovieUi>

    @Query("SELECT * FROM movies where id = :id and type = :type")
    fun getMovie(id: Int, type: String = Movie.TYPE): Flow<MovieUi>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movies: List<MovieUi>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movie: MovieUi)

    @Query("DELETE FROM movies where type= :type")
    suspend fun deleteAll(type: String)

    @Delete
    suspend fun delete(movie: MovieUi)

    @Delete
    suspend fun delete(movie: List<MovieUi>)

    @Query("SELECT COUNT(id) from movies where type= :type")
    suspend fun countMovie(type: String = Movie.TYPE): Int

    @Query("SELECT COUNT(id) from movies where type= :type")
    suspend fun countTvShow(type: String = TvShow.TYPE): Int

    @Query("SELECT * FROM movies where type = :type order by createdAt ASC")
    fun getAllTvShow(type: String = TvShow.TYPE): PagingSource<Int, MovieUi>

    @Query("SELECT * FROM movies where id = :id and type = :type")
    fun getTvShow(id: Int, type: String = TvShow.TYPE): Flow<MovieUi>

    @Query("Select createdAt From movies where type = :type order by createdAt asc limit 1")
    suspend fun lastUpdated(type: String = Movie.TYPE): Long

    @Update
    suspend fun updateMovies(movie: MovieUi)

}