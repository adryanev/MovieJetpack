package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.lifecycle.LiveData
import androidx.paging.PagingSource
import androidx.room.*
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import kotlinx.coroutines.flow.Flow

@Dao
interface TvShowDao {
    @Query("SELECT * FROM tvshows order by popularity DESC")
    fun getAllTvShow(): PagingSource<Int, TvShow>

    @Query("SELECT * FROM tvshows where id = :id")
    fun getTvShow(id: Int): Flow<TvShow>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insert(tvShows: List<TvShow>?)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(tvShow: TvShow)

    @Query("DELETE FROM tvshows")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(tvShow: TvShow)

    @Delete
   suspend fun deleteAll(tvShow: List<TvShow>)

    @Query("SELECT COUNT(id) from tvshows")
    suspend fun count(): Int
}