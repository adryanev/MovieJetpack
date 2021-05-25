package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import androidx.lifecycle.ViewModel
import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel
    
    @Before
    fun setUp() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val shows = viewModel.getTvShows()
        assertNotNull(shows)
        assertEquals(10, shows.size)
    }
}