package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.factory.createMovie
import dev.adryanev.dicoding.moviejetpack.factory.createMovieListResponse
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MovieViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: MovieViewModel
    private val movieRepository = mock<MovieRepository>()
    private val movie = createMovie()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @Test
    fun getDataSuccessTest() {
        testCoroutineRule.runBlockingTest {
            val fakeData = createMovieListResponse()
            val observer = mock<Observer<List<Movie>>>()

            viewModel.itemList.observeForever(observer)

            `when`(movieRepository.getMovieList()).thenReturn(fakeData)
            // when
            viewModel.loadData()

            // then
            assertEquals(4, viewModel.itemList.value?.size)
            assertEquals(movie.id, viewModel.itemList.value?.getOrNull(0)?.id)
            assertEquals(movie.title, viewModel.itemList.value?.getOrNull(0)?.title)
            assertEquals(movie.originalTitle, viewModel.itemList.value?.getOrNull(0)?.originalTitle)
            assertEquals(movie.originalLanguage, viewModel.itemList.value?.getOrNull(0)?.originalLanguage)
            assertEquals(movie.backdropPath, viewModel.itemList.value?.getOrNull(0)?.backdropPath)
            assertEquals(movie.posterPath, viewModel.itemList.value?.getOrNull(0)?.posterPath)
            assertEquals(movie.genreIds, viewModel.itemList.value?.getOrNull(0)?.genreIds)
            assertEquals(movie.voteAverage, viewModel.itemList.value?.getOrNull(0)?.voteAverage)
            assertEquals(movie.voteCount, viewModel.itemList.value?.getOrNull(0)?.voteCount)
            assertEquals(movie.overview, viewModel.itemList.value?.getOrNull(0)?.overview)
            assertEquals(movie.popularity, viewModel.itemList.value?.getOrNull(0)?.popularity)
            assertEquals(movie.mediaType, viewModel.itemList.value?.getOrNull(0)?.mediaType)


            verify(observer).onChanged(fakeData.first().data?.results)
        }
    }
}