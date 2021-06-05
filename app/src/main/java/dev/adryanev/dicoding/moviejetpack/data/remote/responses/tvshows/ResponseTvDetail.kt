package dev.adryanev.dicoding.moviejetpack.data.remote.responses.tvshows

import com.squareup.moshi.Json

data class ResponseTvDetail(

	@Json(name="original_language")
	val originalLanguage: String? = null,

	@Json(name="number_of_episodes")
	val numberOfEpisodes: Int? = null,

	@Json(name="networks")
	val networks: List<NetworksItem?>? = null,

	@Json(name="type")
	val type: String? = null,

	@Json(name="backdrop_path")
	val backdropPath: String? = null,

	@Json(name="genres")
	val genres: List<GenresItem?>? = null,

	@Json(name="popularity")
	val popularity: Double? = null,

	@Json(name="production_countries")
	val productionCountries: List<ProductionCountriesItem?>? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="number_of_seasons")
	val numberOfSeasons: Int? = null,

	@Json(name="vote_count")
	val voteCount: Int? = null,

	@Json(name="first_air_date")
	val firstAirDate: String? = null,

	@Json(name="overview")
	val overview: String? = null,

	@Json(name="seasons")
	val seasons: List<SeasonsItem?>? = null,

	@Json(name="languages")
	val languages: List<String?>? = null,

	@Json(name="created_by")
	val createdBy: List<CreatedByItem?>? = null,

	@Json(name="last_episode_to_air")
	val lastEpisodeToAir: LastEpisodeToAir? = null,

	@Json(name="poster_path")
	val posterPath: String? = null,

	@Json(name="origin_country")
	val originCountry: List<String?>? = null,

	@Json(name="spoken_languages")
	val spokenLanguages: List<SpokenLanguagesItem?>? = null,

	@Json(name="production_companies")
	val productionCompanies: List<ProductionCompaniesItem?>? = null,

	@Json(name="original_name")
	val originalName: String? = null,

	@Json(name="vote_average")
	val voteAverage: Double? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="tagline")
	val tagline: String? = null,

	@Json(name="episode_run_time")
	val episodeRunTime: List<Int?>? = null,

	@Json(name="next_episode_to_air")
	val nextEpisodeToAir: Any? = null,

	@Json(name="in_production")
	val inProduction: Boolean? = null,

	@Json(name="last_air_date")
	val lastAirDate: String? = null,

	@Json(name="homepage")
	val homepage: String? = null,

	@Json(name="status")
	val status: String? = null
)

data class LastEpisodeToAir(

	@Json(name="production_code")
	val productionCode: String? = null,

	@Json(name="air_date")
	val airDate: String? = null,

	@Json(name="overview")
	val overview: String? = null,

	@Json(name="episode_number")
	val episodeNumber: Int? = null,

	@Json(name="vote_average")
	val voteAverage: Double? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="season_number")
	val seasonNumber: Int? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="still_path")
	val stillPath: Any? = null,

	@Json(name="vote_count")
	val voteCount: Int? = null
)

data class ProductionCountriesItem(

	@Json(name="iso_3166_1")
	val iso31661: String? = null,

	@Json(name="name")
	val name: String? = null
)

data class GenresItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null
)

data class CreatedByItem(

	@Json(name="gender")
	val gender: Int? = null,

	@Json(name="credit_id")
	val creditId: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="profile_path")
	val profilePath: Any? = null,

	@Json(name="id")
	val id: Int? = null
)

data class SpokenLanguagesItem(

	@Json(name="name")
	val name: String? = null,

	@Json(name="iso_639_1")
	val iso6391: String? = null,

	@Json(name="english_name")
	val englishName: String? = null
)

data class ProductionCompaniesItem(

	@Json(name="logo_path")
	val logoPath: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="origin_country")
	val originCountry: String? = null
)

data class NetworksItem(

	@Json(name="logo_path")
	val logoPath: String? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="origin_country")
	val originCountry: String? = null
)

data class SeasonsItem(

	@Json(name="air_date")
	val airDate: Any? = null,

	@Json(name="overview")
	val overview: String? = null,

	@Json(name="episode_count")
	val episodeCount: Int? = null,

	@Json(name="name")
	val name: String? = null,

	@Json(name="season_number")
	val seasonNumber: Int? = null,

	@Json(name="id")
	val id: Int? = null,

	@Json(name="poster_path")
	val posterPath: String? = null
)
