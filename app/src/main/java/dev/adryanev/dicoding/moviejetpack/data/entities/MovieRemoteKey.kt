package dev.adryanev.dicoding.moviejetpack.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "movie_remote_key")
data class MovieRemoteKey(
    @PrimaryKey
    val repoId: Int,
    val nextKey: Int?,
    val type: String,
    val createdAt: Date?
)