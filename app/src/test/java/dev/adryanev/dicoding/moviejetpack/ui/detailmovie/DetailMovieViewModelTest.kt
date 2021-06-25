package dev.adryanev.dicoding.moviejetpack.ui.detailmovie

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.data.repositories.FavoriteRepository
import dev.adryanev.dicoding.moviejetpack.factory.*
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class DetailMovieViewModelTest : BaseViewModelTest() {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val movie: MovieUi = createMovie()
    private val tvShow = createTvShow()
    private val favoriteMovie = createFavoriteMovie()
    private val favoriteTvShow = createFavoriteTvShow()
    private val favoriteRepository = mock<FavoriteRepository>()
    private val observer = mock<Observer<FavoriteAndMovie>>()

    @Before
    override fun setup() {
        detailMovieViewModel = DetailMovieViewModel(favoriteRepository)
    }

    @Test
    fun getMovie() {
        runBlockingTest {
            detailMovieViewModel.movie.value = movie

            assertNotNull(detailMovieViewModel.movie)
            assertEquals(detailMovieViewModel.movie.value, movie)
            assertEquals(detailMovieViewModel.movie.value?.originalTitle, movie.originalTitle)
            assertEquals(detailMovieViewModel.movie.value?.originalLanguage, movie.originalLanguage)
            assertEquals(detailMovieViewModel.movie.value?.backdropPath, movie.backdropPath)
            assertEquals(detailMovieViewModel.movie.value?.genreIds, movie.genreIds)
            assertEquals(detailMovieViewModel.movie.value?.posterPath, movie.posterPath)
            assertEquals(detailMovieViewModel.movie.value?.voteCount, movie.voteCount)
            assertEquals(detailMovieViewModel.movie.value?.voteAverage, movie.voteAverage)
            assertEquals(detailMovieViewModel.movie.value?.title, movie.title)
            assertEquals(detailMovieViewModel.movie.value?.overview, movie.overview)
            assertEquals(detailMovieViewModel.movie.value?.releaseDate, movie.releaseDate)
            assertEquals(detailMovieViewModel.movie.value?.id, movie.id)
            assertEquals(detailMovieViewModel.movie.value?.popularity, movie.popularity)
            assertEquals(detailMovieViewModel.movie.value?.mediaType, movie.mediaType)

            `when`(favoriteRepository.getFavoriteMovieById(detailMovieViewModel.movie.value?.id!!)).thenReturn(
                createFavoriteMovieAsFlow()
            )
            detailMovieViewModel.getMovieFavorite(detailMovieViewModel.movie.value?.id!!)

            verify(favoriteRepository).getFavoriteMovieById(detailMovieViewModel.movie.value?.id!!)
            detailMovieViewModel.favorite.observeForever(observer)
            assertEquals(detailMovieViewModel.favorite.value, favoriteMovie)
            assertEquals(detailMovieViewModel.favorite.value?.favorite, favoriteMovie.favorite)
            assertEquals(
                detailMovieViewModel.favorite.value?.favorite?.favoriteId!!,
                favoriteMovie.favorite?.favoriteId
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.favorite?.movieId!!,
                favoriteMovie.favorite?.movieId
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.favorite?.movieType!!,
                favoriteMovie.favorite?.movieType
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.favorite?.createdAt!!,
                favoriteMovie.favorite?.createdAt
            )

            assertEquals(detailMovieViewModel.favorite.value?.movie, movie)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.id, movie.id)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.title, movie.title)
            assertEquals(
                detailMovieViewModel.favorite.value?.movie?.originalTitle,
                movie.originalTitle
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.movie?.originalLanguage,
                movie.originalLanguage
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.movie?.backdropPath,
                movie.backdropPath
            )
            assertEquals(detailMovieViewModel.favorite.value?.movie?.posterPath, movie.posterPath)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.genreIds, movie.genreIds)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.voteAverage, movie.voteAverage)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.voteCount, movie.voteCount)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.overview, movie.overview)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.popularity, movie.popularity)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.mediaType, movie.mediaType)

            verify(observer).onChanged(detailMovieViewModel.favorite.value)
        }

    }

    @Test
    fun getTvShow() {
        runBlockingTest {
            detailMovieViewModel.movie.value = tvShow

            assertNotNull(detailMovieViewModel.movie)
            assertEquals(detailMovieViewModel.movie.value, tvShow)
            assertEquals(detailMovieViewModel.movie.value?.originalTitle, tvShow.originalTitle)
            assertEquals(detailMovieViewModel.movie.value?.originalLanguage, tvShow.originalLanguage)
            assertEquals(detailMovieViewModel.movie.value?.backdropPath, tvShow.backdropPath)
            assertEquals(detailMovieViewModel.movie.value?.genreIds, tvShow.genreIds)
            assertEquals(detailMovieViewModel.movie.value?.posterPath, tvShow.posterPath)
            assertEquals(detailMovieViewModel.movie.value?.voteCount, tvShow.voteCount)
            assertEquals(detailMovieViewModel.movie.value?.voteAverage, tvShow.voteAverage)
            assertEquals(detailMovieViewModel.movie.value?.title, tvShow.title)
            assertEquals(detailMovieViewModel.movie.value?.overview, tvShow.overview)
            assertEquals(detailMovieViewModel.movie.value?.releaseDate, tvShow.releaseDate)
            assertEquals(detailMovieViewModel.movie.value?.id, tvShow.id)
            assertEquals(detailMovieViewModel.movie.value?.popularity, tvShow.popularity)
            assertEquals(detailMovieViewModel.movie.value?.mediaType, tvShow.mediaType)

            `when`(favoriteRepository.getFavoriteMovieById(detailMovieViewModel.movie.value?.id!!)).thenReturn(
                createFavoriteTvShowAsFlow()
            )
            detailMovieViewModel.getMovieFavorite(detailMovieViewModel.movie.value?.id!!)

            verify(favoriteRepository).getFavoriteMovieById(detailMovieViewModel.movie.value?.id!!)
            detailMovieViewModel.favorite.observeForever(observer)
            assertEquals(detailMovieViewModel.favorite.value, favoriteTvShow)
            assertEquals(detailMovieViewModel.favorite.value?.favorite, favoriteTvShow.favorite)
            assertEquals(
                detailMovieViewModel.favorite.value?.favorite?.favoriteId!!,
                favoriteTvShow.favorite?.favoriteId
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.favorite?.movieId!!,
                favoriteTvShow.favorite?.movieId
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.favorite?.movieType!!,
                favoriteTvShow.favorite?.movieType
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.favorite?.createdAt!!,
                favoriteTvShow.favorite?.createdAt
            )

            assertEquals(detailMovieViewModel.favorite.value?.movie, tvShow)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.id, tvShow.id)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.title, tvShow.title)
            assertEquals(
                detailMovieViewModel.favorite.value?.movie?.originalTitle,
                tvShow.originalTitle
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.movie?.originalLanguage,
                tvShow.originalLanguage
            )
            assertEquals(
                detailMovieViewModel.favorite.value?.movie?.backdropPath,
                tvShow.backdropPath
            )
            assertEquals(detailMovieViewModel.favorite.value?.movie?.posterPath, tvShow.posterPath)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.genreIds, tvShow.genreIds)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.voteAverage, tvShow.voteAverage)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.voteCount, tvShow.voteCount)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.overview, tvShow.overview)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.popularity, tvShow.popularity)
            assertEquals(detailMovieViewModel.favorite.value?.movie?.mediaType, tvShow.mediaType)

            verify(observer).onChanged(detailMovieViewModel.favorite.value)
        }
    }
}