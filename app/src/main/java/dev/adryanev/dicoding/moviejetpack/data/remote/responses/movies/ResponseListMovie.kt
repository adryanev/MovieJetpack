package dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies

data class ResponseListMovie(
	val page: Int? = null,
	val totalPages: Int? = null,
	val results: List<ResultMovie?>? = null,
	val totalResults: Int? = null
)

data class ResultMovie(
	val overview: String? = null,
	val originalLanguage: String? = null,
	val originalTitle: String? = null,
	val video: Boolean? = null,
	val title: String? = null,
	val genreIds: List<Int?>? = null,
	val posterPath: String? = null,
	val backdropPath: String? = null,
	val releaseDate: String? = null,
	val mediaType: String? = null,
	val voteAverage: Double? = null,
	val popularity: Double? = null,
	val id: Int? = null,
	val adult: Boolean? = null,
	val voteCount: Int? = null
)

