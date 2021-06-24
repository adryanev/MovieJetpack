package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagedViewModel
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BasePagedViewModel<MovieUi>() {

    init {
        getMovieList()
    }
    private fun getMovieList() = launchPagingAsync(
        execute = {
        movieRepository.getMovieList().cachedIn(viewModelScope)
    },
        onSuccess = {
            itemList = it
        }
    )

}