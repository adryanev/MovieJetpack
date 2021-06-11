package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie order by popularity DESC")
    fun getAllMovie(): Flow<List<Movie>?>

    @Query("SELECT * FROM movie where id = :id")
    fun getMovie(id:Int): Flow<Movie?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: List<Movie>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Delete
    fun delete(movie: Movie)

    @Delete
    fun delete(movie: List<Movie>)
}