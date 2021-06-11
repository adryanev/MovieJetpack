package dev.adryanev.dicoding.moviejetpack.data.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import dev.adryanev.dicoding.moviejetpack.BuildConfig
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "tvshows")
data class TvShow(

    @Json(name="first_air_date")
    val firstAirDate: String? = null,

    @Json(name="overview")
    val overview: String? = null,

    @Json(name="original_language")
    val originalLanguage: String? = null,

    @Json(name="genre_ids")
    val genreIds: List<Int?>? = null,

    @Json(name="poster_path")
    val posterPath: String? = null,

    @Json(name="origin_country")
    val originCountry: List<String?>? = null,

    @Json(name="backdrop_path")
    val backdropPath: String? = null,

    @Json(name="media_type")
    val mediaType: String? = null,

    @Json(name="vote_average")
    val voteAverage: Double? = null,

    @Json(name="original_name")
    val originalName: String? = null,

    @Json(name="popularity")
    val popularity: Double? = null,

    @Json(name="name")
    val name: String? = null,

    @PrimaryKey(autoGenerate = false)
    @Json(name="id")
    val id: Int? = null,

    @Json(name="vote_count")
    val voteCount: Int? = null
): Parcelable {
    fun getFullPosterPath() =
        if (posterPath.isNullOrBlank()) null else BuildConfig.SMALL_IMAGE_URL + posterPath
}

