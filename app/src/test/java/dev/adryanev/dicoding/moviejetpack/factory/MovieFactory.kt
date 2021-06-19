package dev.adryanev.dicoding.moviejetpack.factory

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies.ResponseListMovie
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.tvshows.ResponseListTv
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun createMovieListResponse(): Flow<Resource<ResponseListMovie>> = flow {
    val response = ResponseListMovie()
    val movie1 = Movie(
        originalTitle = "Cruella",
        posterPath = "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
        id = 337404,
        video = false,
        voteAverage = 8.7,
        overview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
        releaseDate = "2021-05-26",
        adult = false,
        backdropPath = "/57Jl9wB8Fv37S06z9mwfyoSpHof.jpg",
        voteCount = 2203,
        genreIds = listOf(35, 80),
        title = "Cruella",
        originalLanguage = "en",
        popularity = 6683.453,
        mediaType = "movie"
    )
    val movie2 = Movie(
        video = false,
        voteAverage = 8.4,
        overview = "Paranormal investigators Ed and Lorraine Warren encounter what would become one of the most sensational cases from their files. The fight for the soul of a young boy takes them beyond anything they'd ever seen before, to mark the first time in U.S. history that a murder suspect would claim demonic possession as a defense.",
        releaseDate = "2021-05-25",
        id = 423108,
        adult = false,
        backdropPath = "/qi6Edc1OPcyENecGtz8TF0DUr9e.jpg",
        genreIds = listOf(
            27,
            9648,
            53
        ),
        voteCount = 1406,
        originalLanguage = "en",
        originalTitle = "The Conjuring : The Devil Made Me Do It",
        posterPath = "/xbSuFiJbbBWCkyCCKIMfuDCA4yV.jpg",
        title = "The Conjuring : The Devil Made Me Do It",
        popularity = 1615.27,
        mediaType = "movie"
    )
    val movie3 = Movie(id = 3)
    val movie4 = Movie(id = 4)
    response.results = arrayListOf(movie1, movie2, movie3, movie4)
    response.page = 1
    response.totalPages = 1000
    response.totalResults = 20000

    emit(Resource.success(response))

}

fun createTvListResponse(): Flow<Resource<ResponseListTv>> = flow {
    val response = ResponseListTv()
    val tvShow1 = TvShow(
        backdropPath = "/Afp8OhiO0Ajb3NPoCBvfu2pqaeO.jpg",
        originalName = "Loki",
        genreIds = listOf(
            10765,
            10759,
            18
        ),
        originalLanguage = "en",
        posterPath = "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
        voteCount = 20,
        voteAverage = 9.4,
        name = "Loki",
        overview = "After stealing the Tesseract during the events of “Avengers= Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice= face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
        originCountry = listOf("US"),
        firstAirDate = "2021-06-09",
        id = 84958,
        popularity = 702.015,
        mediaType = "tv"
    )
    val tvShow2 = TvShow(id = 2)
    val tvShow3 = TvShow(id = 3)
    val tvShow4 = TvShow(id = 4)
    response.results = arrayListOf(tvShow1, tvShow2, tvShow3, tvShow4)
    response.page = 1
    response.totalPages = 1000
    response.totalResults = 20000

    emit(Resource.success(response))

}

fun createTvShow(): TvShow = TvShow(
    backdropPath = "/Afp8OhiO0Ajb3NPoCBvfu2pqaeO.jpg",
    originalName = "Loki",
    genreIds = listOf(
        10765,
        10759,
        18
    ),
    originalLanguage = "en",
    posterPath = "/kEl2t3OhXc3Zb9FBh1AuYzRTgZp.jpg",
    voteCount = 20,
    voteAverage = 9.4,
    name = "Loki",
    overview = "After stealing the Tesseract during the events of “Avengers= Endgame,” an alternate version of Loki is brought to the mysterious Time Variance Authority, a bureaucratic organization that exists outside of time and space and monitors the timeline. They give Loki a choice= face being erased from existence due to being a “time variant”or help fix the timeline and stop a greater threat.",
    originCountry = listOf("US"),
    firstAirDate = "2021-06-09",
    id = 84958,
    popularity = 702.015,
    mediaType = "tv"
)

fun createMovie(): Movie = Movie(
    originalTitle = "Cruella",
    posterPath = "/A0knvX7rlwTyZSKj8H5NiARb45.jpg",
    id = 337404,
    video = false,
    voteAverage = 8.7,
    overview = "In 1970s London amidst the punk rock revolution, a young grifter named Estella is determined to make a name for herself with her designs. She befriends a pair of young thieves who appreciate her appetite for mischief, and together they are able to build a life for themselves on the London streets. One day, Estella’s flair for fashion catches the eye of the Baroness von Hellman, a fashion legend who is devastatingly chic and terrifyingly haute. But their relationship sets in motion a course of events and revelations that will cause Estella to embrace her wicked side and become the raucous, fashionable and revenge-bent Cruella.",
    releaseDate = "2021-05-26",
    adult = false,
    backdropPath = "/57Jl9wB8Fv37S06z9mwfyoSpHof.jpg",
    voteCount = 2203,
    genreIds = listOf(35, 80),
    title = "Cruella",
    originalLanguage = "en",
    popularity = 6683.453,
    mediaType = "movie"
)