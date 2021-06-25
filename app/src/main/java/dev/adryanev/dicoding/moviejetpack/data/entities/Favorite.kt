package dev.adryanev.dicoding.moviejetpack.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.SET_NULL
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(
    tableName = "favorite",
//    foreignKeys = [ForeignKey(
//        entity = MovieUi::class,
//        parentColumns = ["id"],
//        childColumns = ["movieId"],
//        onDelete = SET_NULL
//    )],
    indices = [Index(value = ["favoriteId"]), Index(value = ["movieId"])]
)
data class Favorite(
    @PrimaryKey(autoGenerate = true)
    var favoriteId: Int? = null,
    var movieId: Int? = null,
    var movieType: String? = null,
    @ColumnInfo(name="created_at")
    var createdAt: Date = Date()
): Parcelable