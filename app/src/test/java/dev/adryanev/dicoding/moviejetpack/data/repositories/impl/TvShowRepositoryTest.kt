package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.factory.createTvListResponse
import dev.adryanev.dicoding.moviejetpack.utils.TestCoroutineRule
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class TvShowRepositoryTest {
    private val repository = mock<TvShowRepository>()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()


    @Test
    fun getTvShowList() {
//        testCoroutineRule.runBlockingTest {
//            Mockito.`when`(repository.getTvShowList()).thenReturn(createTvListResponse())
//            val response = repository.getTvShowList().first()
//            verify(repository).getTvShowList()
//            assertNotNull(response)
//            assertEquals(4, response.data?.results?.size)
//        }

    }

}