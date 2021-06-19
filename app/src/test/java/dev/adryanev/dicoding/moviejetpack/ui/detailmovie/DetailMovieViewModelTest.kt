package dev.adryanev.dicoding.moviejetpack.ui.detailmovie

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.factory.createMovie
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import dev.adryanev.dicoding.moviejetpack.utils.DataDummy
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

@ExperimentalCoroutinesApi
class DetailMovieViewModelTest: BaseViewModelTest() {

    private lateinit var detailMovieViewModel: DetailMovieViewModel
    private val movie: Movie = createMovie()

    @Before
    override fun setup(){
        detailMovieViewModel = DetailMovieViewModel()
    }

    @Test
    fun getMovie(){
        detailMovieViewModel.movie.value = movie

        assertNotNull(detailMovieViewModel.movie)
        assertEquals(detailMovieViewModel.movie.value,movie)
        assertEquals(detailMovieViewModel.movie.value?.originalTitle,movie.originalTitle)
        assertEquals(detailMovieViewModel.movie.value?.originalLanguage,movie.originalLanguage)
        assertEquals(detailMovieViewModel.movie.value?.backdropPath,movie.backdropPath)
        assertEquals(detailMovieViewModel.movie.value?.genreIds,movie.genreIds)
        assertEquals(detailMovieViewModel.movie.value?.posterPath,movie.posterPath)
        assertEquals(detailMovieViewModel.movie.value?.voteCount,movie.voteCount)
        assertEquals(detailMovieViewModel.movie.value?.voteAverage,movie.voteAverage)
        assertEquals(detailMovieViewModel.movie.value?.title,movie.title)
        assertEquals(detailMovieViewModel.movie.value?.overview,movie.overview)
        assertEquals(detailMovieViewModel.movie.value?.releaseDate,movie.releaseDate)
        assertEquals(detailMovieViewModel.movie.value?.id,movie.id)
        assertEquals(detailMovieViewModel.movie.value?.popularity,movie.popularity)
        assertEquals(detailMovieViewModel.movie.value?.mediaType,movie.mediaType)
    }
}