package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import android.content.res.Resources
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
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

    init {
        getTvList()
    }
   private fun getTvList() = launchPagingAsync(
       execute = {
           if(checkIfRequestIsRepeated()) itemList
           tvShowRepository.getTvShowList().cachedIn(viewModelScope)
       },
       onSuccess = {
           itemList = it
       }
   )
}