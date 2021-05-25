package dev.adryanev.dicoding.moviejetpack.ui.detail

import androidx.lifecycle.ViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity
import dev.adryanev.dicoding.moviejetpack.utils.DataDummy

class DetailViewModel : ViewModel() {
    private var isMovie = false
    private var _movie:MovieEntity? = null
    val movie: MovieEntity?
        get() {
            return _movie
        }

    fun setSelectedMovie(movie: MovieEntity, isMovie:Boolean){
        this._movie = movie
        this.isMovie = isMovie
    }


}