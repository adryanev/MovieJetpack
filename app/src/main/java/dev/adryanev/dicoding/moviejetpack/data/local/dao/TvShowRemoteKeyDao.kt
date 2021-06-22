package dev.adryanev.dicoding.moviejetpack.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShowRemoteKey

@Dao
interface TvShowRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<TvShowRemoteKey>)

    @Query("SELECT * FROM tvshow_remote_key WHERE repoId = :repoId")
    fun getRemoteKeyById(repoId: Int): TvShowRemoteKey?

    @Query("DELETE FROM tvshow_remote_key")
    suspend fun clearRemoteKeys()
}