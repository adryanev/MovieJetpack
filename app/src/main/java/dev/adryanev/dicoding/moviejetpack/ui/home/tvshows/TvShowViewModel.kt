package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import androidx.lifecycle.ViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity
import dev.adryanev.dicoding.moviejetpack.utils.DataDummy

class TvShowViewModel : ViewModel() {

    fun getTvShows(): List<MovieEntity> = DataDummy.generateTvShows()
}