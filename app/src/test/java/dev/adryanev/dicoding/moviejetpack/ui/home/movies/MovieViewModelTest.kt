package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import androidx.lifecycle.Observer
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.utils.mock
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.factory.createMovieListResponse
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class MovieViewModelTest: BaseViewModelTest(){

    private lateinit var viewModel: MovieViewModel
    private val movieRepository = mock<MovieRepository>()

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
           Assert.assertEquals(4, viewModel.itemList.value?.size)
           Assert.assertEquals("1", viewModel.itemList.value?.getOrNull(0)?.id)
           Assert.assertEquals("2", viewModel.itemList.value?.getOrNull(1)?.id)
           Assert.assertEquals("3", viewModel.itemList.value?.getOrNull(2)?.id)
           Assert.assertEquals("4", viewModel.itemList.value?.getOrNull(3)?.id)

           fakeData.collect{
               Mockito.verify(observer).onChanged()

           }
       }
    }
}