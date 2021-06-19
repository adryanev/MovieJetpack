package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BaseListViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : BaseListViewModel<TvShow>() {

    override fun loadData() {
        viewModelScope.launch {
            try {
                tvShowRepository.getTvShowList().collect {
                    onLoadSuccess(it.data?.results)
                }

            } catch (exception: Exception) {
                onError(exception)
            }
        }
    }
}