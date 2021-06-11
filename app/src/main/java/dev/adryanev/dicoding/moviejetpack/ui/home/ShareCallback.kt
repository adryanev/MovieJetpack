package dev.adryanev.dicoding.moviejetpack.ui.home

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity

interface ShareCallback {
    fun onShareClick(movie: Movie)
}