package dev.adryanev.dicoding.moviejetpack.ui.home

import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity

interface MovieCallback {
    fun onShareClick(movie: MovieEntity)
    fun onItemClick(movie:MovieEntity)
}