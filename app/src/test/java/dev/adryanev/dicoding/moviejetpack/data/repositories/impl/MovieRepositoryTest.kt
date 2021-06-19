package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import com.nhaarman.mockitokotlin2.stub
import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.remote.MovieRemoteDataSource
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.factory.createMovieListResponse
import dev.adryanev.dicoding.moviejetpack.utils.TestCoroutineRule
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    private val repository = mock<MovieRepository>()
    private val remoteDataSource = mock<MovieRemoteDataSource>()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Test
    fun getMovieList() {
        testCoroutineRule.runBlockingTest {
           `when`(repository.getMovieList()).thenReturn(createMovieListResponse())
            val response = repository.getMovieList().first()
            verify(repository).getMovieList()
            assertNotNull(response)
            assertEquals(4, response.data?.results?.size)
        }

    }
}