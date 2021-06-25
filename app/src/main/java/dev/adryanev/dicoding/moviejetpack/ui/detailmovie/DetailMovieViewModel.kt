package dev.adryanev.dicoding.moviejetpack.ui.detailmovie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import dev.adryanev.dicoding.moviejetpack.data.repositories.FavoriteRepository
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(val favoriteRepository: FavoriteRepository) :
    BaseViewModel() {

    val movie = MutableLiveData<MovieUi>()
    val favorite = MutableLiveData<FavoriteAndMovie>()

    fun setBookmark(movie: FavoriteAndMovie, state: Boolean) {
        viewModelScope.launch {
            favoriteRepository.setMovieFavorite(movie, state)
        }
    }


    fun getMovieFavorite(id: Int) {

        viewModelScope.launch {
            favoriteRepository.getFavoriteMovieById(id).collectLatest {
                favorite.value = it
            }
        }
    }



}