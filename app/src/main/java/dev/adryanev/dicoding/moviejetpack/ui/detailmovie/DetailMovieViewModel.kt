package dev.adryanev.dicoding.moviejetpack.ui.detailmovie

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import dev.adryanev.dicoding.moviejetpack.data.repositories.FavoriteRepository
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(val favoriteRepository: FavoriteRepository) :
    BaseViewModel() {

    val movie = MutableLiveData<MovieUi>()
    val movieUiAndFavorite: LiveData<MovieUiAndFavorite> =
        liveData { getMovieFavorite(movie.value?.id!!) }

    fun setBookmark(movie: MovieUiAndFavorite, state: Boolean) {
        viewModelScope.launch {
            favoriteRepository.setMovieFavorite(movie, state)
        }
    }


    suspend fun getMovieFavorite(id: Int): LiveData<MovieUiAndFavorite> {

        return favoriteRepository.getFavoriteMovieById(id).asLiveData()


    }
}