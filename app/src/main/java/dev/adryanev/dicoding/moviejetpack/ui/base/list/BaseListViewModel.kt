package dev.adryanev.dicoding.moviejetpack.ui.base.list

import androidx.lifecycle.MutableLiveData
import dev.adryanev.dicoding.moviejetpack.data.constants.Constants
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel
import timber.log.Timber

abstract class BaseListViewModel<Item> : BaseViewModel() {

    val isRefreshing = MutableLiveData<Boolean>().apply { value =false }

    val itemList = MutableLiveData<ArrayList<Item>>()
    val isEmptyList = MutableLiveData<Boolean>().apply { value = false }

    abstract fun loadData()

    private fun isFirst() = itemList.value?.isEmpty() ?: true

    fun firstLoad(){
        if (isFirst()) {
            showLoading()
            loadData()
        }
    }

    fun doRefresh(){
        if(isLoading.value == true || isRefreshing.value == true) return
        isRefreshing.value = true
        refreshData()
    }

    private fun refreshData() {
        itemList.value?.clear()
        loadData()
    }

    fun onLoadSuccess(items : List<Item>?){

        val newList = itemList.value ?: ArrayList()
        newList.addAll(items ?: listOf())
        itemList.value = newList
        hideLoading()
        isRefreshing.value = false


        checkEmptyList()
    }

    override fun onError(throwable: Throwable) {
        super.onError(throwable)

        isRefreshing.value =false
        checkEmptyList()
    }
    private fun checkEmptyList() {
        isEmptyList.value = itemList.value?.isEmpty() ?: true
    }


}
