package dev.adryanev.dicoding.moviejetpack.data.remote.responses.tvshows

data class ResponseListTv(
	val page: Int? = null,
	val totalPages: Int? = null,
	val results: List<ResultTv?>? = null,
	val totalResults: Int? = null
)

data class ResultTv(
	val firstAirDate: String? = null,
	val overview: String? = null,
	val originalLanguage: String? = null,
	val genreIds: List<Int?>? = null,
	val posterPath: String? = null,
	val originCountry: List<String?>? = null,
	val backdropPath: String? = null,
	val mediaType: String? = null,
	val voteAverage: Double? = null,
	val originalName: String? = null,
	val popularity: Double? = null,
	val name: String? = null,
	val id: Int? = null,
	val voteCount: Int? = null
)

