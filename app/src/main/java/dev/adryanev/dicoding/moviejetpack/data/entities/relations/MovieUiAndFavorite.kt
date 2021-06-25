package dev.adryanev.dicoding.moviejetpack.data.entities.relations

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieUiAndFavorite(
    @Embedded
    val favorite: Favorite?,
    @Relation(
        parentColumn = "movieId",
        entityColumn = "id"
    )
    val movie: MovieUi
):Parcelable