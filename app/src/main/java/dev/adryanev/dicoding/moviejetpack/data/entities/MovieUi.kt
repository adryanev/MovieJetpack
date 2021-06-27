package dev.adryanev.dicoding.moviejetpack.data.entities

import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.adryanev.dicoding.moviejetpack.BuildConfig
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "movies")
data class MovieUi(
    @PrimaryKey
    var id: Int?,
    var overview: String? = null,
    var originalLanguage: String? = null,
    var originalTitle: String? = null,
    var title: String? = null,
    var genreIds: List<Int?>? = null,
    var posterPath: String? = null,
    var backdropPath: String? = null,
    var releaseDate: String? = null,
    var mediaType: String? = null,
    var voteAverage: Double? = null,
    var popularity: Double? = null,
    var voteCount: Int? = null,
    var type: String? = null,
    var createdAt: Date? = null,

) : Parcelable {
    fun getFullPosterPath() =
        if (posterPath.isNullOrBlank()) null else BuildConfig.SMALL_IMAGE_URL + posterPath
}