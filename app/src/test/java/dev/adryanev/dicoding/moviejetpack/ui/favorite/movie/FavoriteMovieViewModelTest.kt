package dev.adryanev.dicoding.moviejetpack.ui.favorite.movie

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.repositories.FavoriteRepository
import dev.adryanev.dicoding.moviejetpack.factory.createFavoriteResponse
import dev.adryanev.dicoding.moviejetpack.factory.expectedFavoriteMovieResult
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import dev.adryanev.dicoding.moviejetpack.utils.collectData
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class FavoriteMovieViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: FavoriteMovieViewModel
    private val repository = mock<FavoriteRepository>()
    private val favorite = expectedFavoriteMovieResult()

    @Before
    fun setUp() {
        viewModel = FavoriteMovieViewModel(repository)
    }

    @ExperimentalTime
    @ExperimentalCoroutinesApi
    @Test
    fun getFavoriteMovie() {
        testCoroutineRule.runBlockingTest {
            val fakeData = createFavoriteResponse()

            Mockito.`when`(repository.getFavoriteMovies()).thenReturn(fakeData)

            verify(repository).getFavoriteMovies()
            launchTest {
                viewModel.itemList?.test(timeout = Duration.ZERO, validate = {
                    val collectedData = expectItem().collectData()
                    assertEquals(4, collectedData.size)
                    assertEquals(favorite, collectedData)
                    assertEquals(favorite[0].favorite, collectedData[0].favorite)
                    assertEquals(favorite[0].favorite?.favoriteId, collectedData[0].favorite?.favoriteId)
                    assertEquals(favorite[0].favorite?.movieId, collectedData[0].favorite?.movieId)
                    assertEquals(favorite[0].favorite?.movieType, collectedData[0].favorite?.movieType)
                    assertEquals(favorite[0].favorite?.createdAt, collectedData[0].favorite?.createdAt)


                    assertEquals(favorite[0].movie, collectedData[0].movie)
                    assertEquals(favorite[0].movie.id, collectedData[0].movie.id)
                    assertEquals(favorite[0].movie.title, collectedData[0].movie.title)
                    assertEquals(favorite[0].movie.originalTitle, collectedData[0].movie.originalTitle)
                    assertEquals(favorite[0].movie.originalLanguage, collectedData[0].movie.originalLanguage)
                    assertEquals(favorite[0].movie.backdropPath, collectedData[0].movie.backdropPath)
                    assertEquals(favorite[0].movie.posterPath, collectedData[0].movie.posterPath)
                    assertEquals(favorite[0].movie.genreIds, collectedData[0].movie.genreIds)
                    assertEquals(favorite[0].movie.voteAverage, collectedData[0].movie.voteAverage)
                    assertEquals(favorite[0].movie.voteCount, collectedData[0].movie.voteCount)
                    assertEquals(favorite[0].movie.overview, collectedData[0].movie.overview)
                    assertEquals(favorite[0].movie.popularity, collectedData[0].movie.popularity)
                    assertEquals(favorite[0].movie.mediaType, collectedData[0].movie.mediaType)

                    expectComplete()

                })
            }
        }
    }
}