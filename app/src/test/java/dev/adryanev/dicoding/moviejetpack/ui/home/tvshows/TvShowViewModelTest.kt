package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.factory.createMovieListResponse
import dev.adryanev.dicoding.moviejetpack.factory.createTvListResponse
import dev.adryanev.dicoding.moviejetpack.factory.createTvShow
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import dev.adryanev.dicoding.moviejetpack.ui.home.movies.MovieViewModel
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import org.junit.Assert
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mock
import org.mockito.Mockito

@ExperimentalCoroutinesApi
class TvShowViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: TvShowViewModel
    private val tvRepository = mock<TvShowRepository>()
    private val tv = createTvShow()

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvRepository)
    }

    @Test
    fun getDataSuccessTest() {
        testCoroutineRule.runBlockingTest {
            val fakeData = createTvListResponse()
            val observer = mock<Observer<List<TvShow>>>()

            viewModel.itemList.observeForever(observer)

            Mockito.`when`(tvRepository.getTvShowList()).thenReturn(fakeData)
            // when
            viewModel.loadData()

            // then
            assertEquals(4, viewModel.itemList.value?.size)
            assertEquals(tv.id, viewModel.itemList.value?.getOrNull(0)?.id)
            assertEquals(tv.name, viewModel.itemList.value?.getOrNull(0)?.name)
            assertEquals(tv.originalName, viewModel.itemList.value?.getOrNull(0)?.originalName)
            assertEquals(tv.originalLanguage, viewModel.itemList.value?.getOrNull(0)?.originalLanguage)
            assertEquals(tv.backdropPath, viewModel.itemList.value?.getOrNull(0)?.backdropPath)
            assertEquals(tv.posterPath, viewModel.itemList.value?.getOrNull(0)?.posterPath)
            assertEquals(tv.genreIds, viewModel.itemList.value?.getOrNull(0)?.genreIds)
            assertEquals(tv.voteAverage, viewModel.itemList.value?.getOrNull(0)?.voteAverage)
            assertEquals(tv.voteCount, viewModel.itemList.value?.getOrNull(0)?.voteCount)
            assertEquals(tv.overview, viewModel.itemList.value?.getOrNull(0)?.overview)
            assertEquals(tv.popularity, viewModel.itemList.value?.getOrNull(0)?.popularity)
            assertEquals(tv.mediaType, viewModel.itemList.value?.getOrNull(0)?.mediaType)

            verify(observer).onChanged(fakeData.first().data?.results)
        }
    }
}