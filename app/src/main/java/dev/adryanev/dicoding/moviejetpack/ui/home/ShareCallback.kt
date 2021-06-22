package dev.adryanev.dicoding.moviejetpack.ui.home

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie

interface ShareCallback {
    fun onShareClick(movie: Movie)
}