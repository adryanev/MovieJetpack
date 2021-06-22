package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movie order by popularity DESC")
    fun getAllMovie(): PagingSource<Int, Movie>

    @Query("SELECT * FROM movie where id = :id")
    fun getMovie(id:Int): Flow<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movies: List<Movie>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)

    @Query("DELETE FROM movie")
    suspend fun deleteAll()

    @Delete
   suspend fun delete(movie: Movie)

    @Delete
    suspend fun delete(movie: List<Movie>)

    @Query("SELECT COUNT(id) from movie")
    suspend fun count(): Int
}