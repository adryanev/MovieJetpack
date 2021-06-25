package dev.adryanev.dicoding.moviejetpack.data.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.ForeignKey.SET_NULL
import androidx.room.Index
import androidx.room.PrimaryKey
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
    indices = [Index(value = ["id"]), Index(value = ["movieId"])]
)
data class Favorite(
    @PrimaryKey
    var id: Int? = null,
    var movieId: Int? = null,
    var type: String? = null,
    var createdAt: Date = Date()
): Parcelable