package dev.adryanev.dicoding.moviejetpack.data.entities

import android.os.Parcelable
import com.squareup.moshi.Json
import dev.adryanev.dicoding.moviejetpack.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(

    @Json(name = "overview")
    val overview: String? = null,

    @Json(name = "original_language")
    val originalLanguage: String? = null,

    @Json(name = "original_title")
    val originalTitle: String? = null,

    @Json(name = "video")
    val video: Boolean? = null,

    @Json(name = "title")
    val title: String? = null,

    @Json(name = "genre_ids")
    val genreIds: List<Int?>? = null,

    @Json(name = "poster_path")
    val posterPath: String? = null,

    @Json(name = "backdrop_path")
    val backdropPath: String? = null,

    @Json(name = "release_date")
    val releaseDate: String? = null,

    @Json(name = "media_type")
    val mediaType: String? = null,

    @Json(name = "vote_average")
    val voteAverage: Double? = null,

    @Json(name = "popularity")
    val popularity: Double? = null,

    @Json(name = "id")
    val id: Int? = null,

    @Json(name = "adult")
    val adult: Boolean? = null,

    @Json(name = "vote_count")
    val voteCount: Int? = null
) : Parcelable {

    fun getFullPosterPath() =
        if (posterPath.isNullOrBlank()) null else BuildConfig.SMALL_IMAGE_URL + posterPath

    companion object {
        const val TYPE = "movie"
    }

}