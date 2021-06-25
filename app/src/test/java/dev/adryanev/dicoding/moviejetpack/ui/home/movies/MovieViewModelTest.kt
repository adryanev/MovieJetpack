package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import androidx.paging.PagingData
import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.factory.createMovie
import dev.adryanev.dicoding.moviejetpack.factory.createMovieListResponse
import dev.adryanev.dicoding.moviejetpack.factory.expectedMovieResult
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import dev.adryanev.dicoding.moviejetpack.utils.collectData
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class MovieViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: MovieViewModel
    private val movieRepository = mock<MovieRepository>()
    private val movie = createMovie()

    @Before
    fun setUp() {
        viewModel = MovieViewModel(movieRepository)
    }

    @ExperimentalTime
    @Test
    fun getDataSuccessTest() {
        testCoroutineRule.runBlockingTest {
            val fakeData = createMovieListResponse()

            `when`(movieRepository.getMovieList()).thenReturn(fakeData)

            verify(movieRepository).getMovieList()
            launchTest {
                viewModel.itemList?.test(timeout = Duration.ZERO, validate = {
                    val collectedData = expectItem().collectData()
                    assertEquals(4,collectedData.size)
                    assertEquals(expectedMovieResult(),collectedData)
                    assertEquals(expectedMovieResult()[0].id, collectedData[0].id)
                    assertEquals(expectedMovieResult()[0].title, collectedData[0].title)
                    assertEquals(expectedMovieResult()[0].originalTitle, collectedData[0].originalTitle)
                    assertEquals(expectedMovieResult()[0].originalLanguage, collectedData[0].originalLanguage)
                    assertEquals(expectedMovieResult()[0].backdropPath, collectedData[0].backdropPath)
                    assertEquals(expectedMovieResult()[0].posterPath, collectedData[0].posterPath)
                    assertEquals(expectedMovieResult()[0].genreIds, collectedData[0].genreIds)
                    assertEquals(expectedMovieResult()[0].voteAverage, collectedData[0].voteAverage)
                    assertEquals(expectedMovieResult()[0].voteCount, collectedData[0].voteCount)
                    assertEquals(expectedMovieResult()[0].overview, collectedData[0].overview)
                    assertEquals(expectedMovieResult()[0].popularity, collectedData[0].popularity)
                    assertEquals(expectedMovieResult()[0].mediaType, collectedData[0].mediaType)
                    expectComplete()

                })
            }
        }
    }
}