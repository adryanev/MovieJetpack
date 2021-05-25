package dev.adryanev.dicoding.moviejetpack.ui.detail

import dev.adryanev.dicoding.moviejetpack.utils.DataDummy
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class DetailViewModelTest {

    private lateinit var viewModel: DetailViewModel
    private val dummyMovie = DataDummy.generateMovies()[0]
    private val dummyTvShow = DataDummy.generateTvShows()[0]
    @Before
    fun setUp() {
        viewModel = DetailViewModel()
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie,true)
        val movie = viewModel.movie
        assertNotNull(movie)
        assertEquals(dummyMovie,movie)
        assertEquals(dummyMovie.title, movie?.title)
        assertEquals(dummyMovie.overview, movie?.overview)
        assertEquals(dummyMovie.releaseDate, movie?.releaseDate)
        assertEquals(dummyMovie.userScore, movie?.userScore)
        assertEquals(dummyMovie.duration, movie?.duration)
        assertEquals(dummyMovie.genre.toString(), movie?.genre.toString())
        assertEquals(dummyMovie.rating, movie?.rating)
        assertEquals(dummyMovie.language, movie?.language)
        assertEquals(dummyMovie.poster, movie?.poster)
        assertEquals(dummyMovie.budget, movie?.budget)
        assertEquals(dummyMovie.revenue, movie?.revenue)

    }
    @Test
    fun getTvShow(){
        viewModel.setSelectedMovie(dummyTvShow, true)
        val tvShow = viewModel.movie
        assertNotNull(tvShow)
        assertEquals(dummyTvShow,tvShow)
        assertEquals(dummyTvShow.title, tvShow?.title)
        assertEquals(dummyTvShow.overview, tvShow?.overview)
        assertEquals(dummyTvShow.releaseDate, tvShow?.releaseDate)
        assertEquals(dummyTvShow.userScore, tvShow?.userScore)
        assertEquals(dummyTvShow.duration, tvShow?.duration)
        assertEquals(dummyTvShow.genre.toString(), tvShow?.genre.toString())
        assertEquals(dummyTvShow.rating, tvShow?.rating)
        assertEquals(dummyTvShow.language, tvShow?.language)
        assertEquals(dummyTvShow.poster, tvShow?.poster)
        assertEquals(dummyTvShow.budget, tvShow?.budget)
        assertEquals(dummyTvShow.revenue, tvShow?.revenue)

    }
}