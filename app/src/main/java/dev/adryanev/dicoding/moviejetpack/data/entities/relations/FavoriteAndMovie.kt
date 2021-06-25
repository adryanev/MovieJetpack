package dev.adryanev.dicoding.moviejetpack.data.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi


data class FavoriteAndMovie(


    @Embedded
    val favorite: Favorite?,

    @Relation(
        parentColumn = "movieId",
        entityColumn = "id"
    )
    val movie: MovieUi

)