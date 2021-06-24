package dev.adryanev.dicoding.moviejetpack.data.mapper

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import java.util.*

fun Movie.toMovieUI(): MovieUi {
    return MovieUi(
        id = id,
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalTitle,
        title = title,
        genreIds = genreIds,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = releaseDate,
        mediaType = mediaType,
        voteAverage = voteAverage,
        popularity = popularity,
        voteCount = voteCount,
        type = Movie.TYPE,
        createdAt = Date()
    )
}

fun TvShow.toMovieUi(): MovieUi {
    return MovieUi(
        id = id,
        overview = overview,
        originalLanguage = originalLanguage,
        originalTitle = originalName,
        title = name,
        genreIds = genreIds,
        posterPath = posterPath,
        backdropPath = backdropPath,
        releaseDate = firstAirDate,
        mediaType = mediaType,
        voteAverage = voteAverage,
        popularity = popularity,
        voteCount = voteCount,
        type = TvShow.TYPE,
        createdAt = Date()

    )
}
