package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.repositories.BaseRepositoryTest
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.factory.createMovie
import dev.adryanev.dicoding.moviejetpack.factory.createMovieListResponse
import dev.adryanev.dicoding.moviejetpack.factory.createTvListResponse
import dev.adryanev.dicoding.moviejetpack.factory.createTvShow
import dev.adryanev.dicoding.moviejetpack.utils.TestCoroutineRule
import dev.adryanev.dicoding.moviejetpack.utils.collectData
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class TvShowRepositoryTest : BaseRepositoryTest() {
    private val repository = mock<TvShowRepository>()
    val movie = createTvShow()

    @ExperimentalTime
    @Test
    fun getMovieList() {
        testCoroutineRule.runBlockingTest {
            Mockito.`when`(repository.getTvShowList()).thenReturn(createTvListResponse())
            val response = repository.getTvShowList()
            verify(repository).getTvShowList()

            launchTest {
                response.test(timeout = Duration.ZERO) {
                    val data = expectItem().collectData()

                    assertNotNull(data)
                    assertEquals(4, data.size)
                    assertEquals(movie.id, data[0].id)
                    assertEquals(movie.title, data[0].title)
                    assertEquals(movie.originalTitle, data[0].originalTitle)
                    assertEquals(movie.originalLanguage, data[0].originalLanguage)
                    assertEquals(movie.backdropPath, data[0].backdropPath)
                    assertEquals(movie.posterPath, data[0].posterPath)
                    assertEquals(movie.genreIds, data[0].genreIds)
                    assertEquals(movie.voteAverage, data[0].voteAverage)
                    assertEquals(movie.voteCount, data[0].voteCount)
                    assertEquals(movie.overview, data[0].overview)
                    assertEquals(movie.popularity, data[0].popularity)
                    assertEquals(movie.mediaType, data[0].mediaType)

                    expectComplete()
                }
            }

        }

    }

}