package dev.adryanev.dicoding.moviejetpack.ui.base.list

import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingData
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel
import kotlinx.coroutines.flow.Flow

abstract class BaseListViewModel<Item : Any> : BaseViewModel() {

    val isRefreshing = MutableLiveData<Boolean>().apply { value = false }

    var itemList: Flow<PagingData<Item>>? = null

    private val isLoadMore = MutableLiveData<Boolean>().apply { value = false }

    fun checkIfRequestIsRepeated(): Boolean =
        itemList != null

}
