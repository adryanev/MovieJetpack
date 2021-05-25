package dev.adryanev.dicoding.moviejetpack.data.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieEntity(
    var id: Int? = 0,
    var title: String? = null,
    var overview: String? = null,
    var releaseDate: String? = null,
    var userScore: Int = 0,
    var duration: Int = 0,
    var genre: Array<String>?,
    var rating: String? = null,
    var language:String? = null,
    var poster: String? = null,
    var budget: Int? = null,
    var revenue: Int? = null
) : Parcelable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as MovieEntity

        if (title != other.title) return false
        if (overview != other.overview) return false
        if (releaseDate != other.releaseDate) return false
        if (userScore != other.userScore) return false
        if (duration != other.duration) return false
        if (genre != null) {
            if (other.genre == null) return false
            if (!genre.contentEquals(other.genre)) return false
        } else if (other.genre != null) return false
        if (rating != other.rating) return false
        if (language != other.language) return false
        if (poster != other.poster) return false
        if (budget != other.budget) return false
        if (revenue != other.revenue) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (overview?.hashCode() ?: 0)
        result = 31 * result + (releaseDate?.hashCode() ?: 0)
        result = 31 * result + userScore
        result = 31 * result + duration
        result = 31 * result + (genre?.contentHashCode() ?: 0)
        result = 31 * result + (rating?.hashCode() ?: 0)
        result = 31 * result + (language?.hashCode() ?: 0)
        result = 31 * result + (poster?.hashCode() ?: 0)
        result = 31 * result + (budget ?: 0)
        result = 31 * result + (revenue ?: 0)
        return result
    }
}