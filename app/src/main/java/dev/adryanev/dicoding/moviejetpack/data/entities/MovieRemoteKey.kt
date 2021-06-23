package dev.adryanev.dicoding.moviejetpack.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movie_remote_key")
data class MovieRemoteKey(
    @PrimaryKey(autoGenerate = true)
     val repoId: Int,
     val nextKey: Int?,
)