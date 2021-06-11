package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshows order by popularity DESC")
    fun getAllTvShow(): Flow<List<TvShow>?>

    @Query("SELECT * FROM tvshows where id = :id")
    fun getTvShow(id: Int): Flow<TvShow?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tvShows: List<TvShow>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tvShow: TvShow)

    @Delete
    fun delete(tvShow: TvShow)

    @Delete
    fun deleteAll(tvShow: List<TvShow>)
}