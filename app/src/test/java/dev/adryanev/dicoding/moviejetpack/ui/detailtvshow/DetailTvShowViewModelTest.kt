package dev.adryanev.dicoding.moviejetpack.ui.detailtvshow

import dev.adryanev.dicoding.moviejetpack.factory.createTvShow
import dev.adryanev.dicoding.moviejetpack.ui.BaseViewModelTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailTvShowViewModelTest : BaseViewModelTest() {

    private lateinit var detailTvShowViewModel: DetailTvShowViewModel
    private val tvShow = createTvShow()

    @Before
    fun setUp() {
        detailTvShowViewModel = DetailTvShowViewModel()
    }

    @Test
    fun getTvShow() {
        detailTvShowViewModel.tvShow.value = tvShow

        assertNotNull(detailTvShowViewModel.tvShow)
        assertEquals(detailTvShowViewModel.tvShow.value, tvShow)
        assertEquals(detailTvShowViewModel.tvShow.value?.originalName, tvShow.originalName)
        assertEquals(detailTvShowViewModel.tvShow.value?.originCountry, tvShow.originCountry)
        assertEquals(detailTvShowViewModel.tvShow.value?.originalLanguage, tvShow.originalLanguage)
        assertEquals(detailTvShowViewModel.tvShow.value?.backdropPath, tvShow.backdropPath)
        assertEquals(detailTvShowViewModel.tvShow.value?.genreIds, tvShow.genreIds)
        assertEquals(detailTvShowViewModel.tvShow.value?.posterPath, tvShow.posterPath)
        assertEquals(detailTvShowViewModel.tvShow.value?.voteCount, tvShow.voteCount)
        assertEquals(detailTvShowViewModel.tvShow.value?.voteAverage, tvShow.voteAverage)
        assertEquals(detailTvShowViewModel.tvShow.value?.name, tvShow.name)
        assertEquals(detailTvShowViewModel.tvShow.value?.overview, tvShow.overview)
        assertEquals(detailTvShowViewModel.tvShow.value?.firstAirDate, tvShow.firstAirDate)
        assertEquals(detailTvShowViewModel.tvShow.value?.id, tvShow.id)
        assertEquals(detailTvShowViewModel.tvShow.value?.popularity, tvShow.popularity)
        assertEquals(detailTvShowViewModel.tvShow.value?.mediaType, tvShow.mediaType)

    }
}