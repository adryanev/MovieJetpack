package dev.adryanev.dicoding.moviejetpack.data.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "favorite",
    primaryKeys = ["id", "movieId"],
    foreignKeys = [ForeignKey(
        entity = MovieUi::class,
        parentColumns = ["id"],
        childColumns = ["movieId"]
    )],
    indices = [Index(value = ["id"]), Index(value = ["movieId"])]
)
data class Favorite(
    @NonNull
    val id: Int,
    val movieId: Int,
    val type: String
)