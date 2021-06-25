package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import app.cash.turbine.test
import com.nhaarman.mockitokotlin2.verify
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.factory.createTvListResponse
import dev.adryanev.dicoding.moviejetpack.factory.expectedTvResult
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import dev.adryanev.dicoding.moviejetpack.utils.collectData
import dev.adryanev.dicoding.moviejetpack.utils.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import kotlin.time.Duration
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
class TvShowViewModelTest : BaseViewModelTest() {

    private lateinit var viewModel: TvShowViewModel
    private val tvRepository = mock<TvShowRepository>()

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(tvRepository)
    }

    @ExperimentalTime
    @Test
    fun getDataSuccessTest() {
        testCoroutineRule.runBlockingTest {
            val fakeData = createTvListResponse()
            `when`(tvRepository.getTvShowList()).thenReturn(fakeData)
            verify(tvRepository).getTvShowList()
            launchTest {
                viewModel.itemList?.test(timeout = Duration.ZERO, validate = {
                    val collectedData = expectItem().collectData()
                    assertEquals(4, collectedData.size)
                    assertEquals(expectedTvResult(), collectedData)
                    assertEquals(expectedTvResult()[0].id, collectedData[0].id)
                    assertEquals(expectedTvResult()[0].title, collectedData[0].title)
                    assertEquals(
                        expectedTvResult()[0].originalTitle,
                        collectedData[0].originalTitle
                    )
                    assertEquals(
                        expectedTvResult()[0].originalLanguage,
                        collectedData[0].originalLanguage
                    )
                    assertEquals(expectedTvResult()[0].backdropPath, collectedData[0].backdropPath)
                    assertEquals(expectedTvResult()[0].posterPath, collectedData[0].posterPath)
                    assertEquals(expectedTvResult()[0].genreIds, collectedData[0].genreIds)
                    assertEquals(expectedTvResult()[0].voteAverage, collectedData[0].voteAverage)
                    assertEquals(expectedTvResult()[0].voteCount, collectedData[0].voteCount)
                    assertEquals(expectedTvResult()[0].overview, collectedData[0].overview)
                    assertEquals(expectedTvResult()[0].popularity, collectedData[0].popularity)
                    assertEquals(expectedTvResult()[0].mediaType, collectedData[0].mediaType)
                    expectComplete()

                })
            }
        }
    }
}