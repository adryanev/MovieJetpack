package dev.adryanev.dicoding.moviejetpack.ui.detailmovie

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor() : BaseViewModel() {

    val movie = MutableLiveData<MovieUi>()


}