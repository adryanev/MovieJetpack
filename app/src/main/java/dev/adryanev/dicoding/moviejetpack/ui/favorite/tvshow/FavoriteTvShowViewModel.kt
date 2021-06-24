package dev.adryanev.dicoding.moviejetpack.ui.favorite.tvshow

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import dev.adryanev.dicoding.moviejetpack.data.repositories.FavoriteRepository
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagedViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteTvShowViewModel @Inject constructor(val favoriteRepository: FavoriteRepository) :
    BasePagedViewModel<MovieUiAndFavorite>() {

    init {
        getFavoriteMovies()
    }

    private fun getFavoriteMovies() = launchPagingAsync(
        execute = {
            favoriteRepository.getFavoriteMovies().cachedIn(viewModelScope)
        },
        onSuccess = {
            itemList = it
        }
    )
}