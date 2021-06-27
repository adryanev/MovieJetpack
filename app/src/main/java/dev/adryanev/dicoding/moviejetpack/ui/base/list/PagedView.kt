package dev.adryanev.dicoding.moviejetpack.ui.base.list

import androidx.paging.CombinedLoadStates
import androidx.paging.PagingData

interface PagedView<T : Any> {

    fun initRecyclerView()
    fun renderList(list: PagingData<T>)
    fun renderLoadState(state: CombinedLoadStates)
}