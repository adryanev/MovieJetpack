package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import androidx.lifecycle.ViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity
import dev.adryanev.dicoding.moviejetpack.utils.DataDummy

class MovieViewModel : ViewModel() {

    fun getMovies(): List<MovieEntity> = DataDummy.generateMovies()
}