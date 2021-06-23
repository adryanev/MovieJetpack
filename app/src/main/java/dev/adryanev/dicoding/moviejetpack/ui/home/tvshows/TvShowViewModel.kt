package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagedViewModel
import javax.inject.Inject

@HiltViewModel
class TvShowViewModel @Inject constructor(
    private val tvShowRepository: TvShowRepository
) : BasePagedViewModel<TvShow>() {

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