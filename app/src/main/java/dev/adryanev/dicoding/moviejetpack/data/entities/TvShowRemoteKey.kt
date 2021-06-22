package dev.adryanev.dicoding.moviejetpack.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tvshow_remote_key")
data class TvShowRemoteKey(
    @PrimaryKey
     val repoId: Int,
     val nextKey: Int?,
     val prevKey: Int?
)