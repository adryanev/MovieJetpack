package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieRemoteKey

@Dao
interface MovieRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<MovieRemoteKey>)

    @Query("SELECT * FROM movie_remote_key WHERE repoId = :repoId")
    fun getRemoteKeyById(repoId: Int): MovieRemoteKey?

    @Query("DELETE FROM movie_remote_key where type= :type")
    suspend fun clearRemoteKeys(type: String)
}