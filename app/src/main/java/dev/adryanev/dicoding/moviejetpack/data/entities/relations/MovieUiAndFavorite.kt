package dev.adryanev.dicoding.moviejetpack.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi

data class MovieUiAndFavorite(
    @Embedded val movie: MovieUi,
    @Relation(
        parentColumn = "id",
        entityColumn = "movieId"
    )
    val favorite: Favorite
)