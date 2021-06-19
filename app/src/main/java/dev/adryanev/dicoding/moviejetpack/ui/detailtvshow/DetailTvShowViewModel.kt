package dev.adryanev.dicoding.moviejetpack.ui.detailtvshow

import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class DetailTvShowViewModel @Inject constructor() : BaseViewModel() {

    val tvShow = MutableLiveData<TvShow>()


}