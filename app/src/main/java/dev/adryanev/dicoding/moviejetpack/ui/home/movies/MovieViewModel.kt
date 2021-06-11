package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BaseListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseListViewModel<Movie>() {

    override fun loadData() {

        viewModelScope.launch {
            try {
                movieRepository.getMovieList().collect {
                    onLoadSuccess(it.data?.results)
                }
            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }
}