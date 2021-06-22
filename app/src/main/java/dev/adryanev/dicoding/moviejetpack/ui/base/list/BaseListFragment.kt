package dev.adryanev.dicoding.moviejetpack.ui.base.list

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseFragment
import dev.adryanev.dicoding.moviejetpack.utils.PagingLoadStateAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter

abstract class BaseListFragment<ViewBinding : ViewDataBinding, ViewModel : BaseListViewModel<Item>, Item : Any> :
    BaseFragment<ViewBinding, ViewModel>() {

    abstract val pagerAdapter: BasePagingAdapter<Item, out ViewDataBinding>
    abstract val swipeRefreshLayout: SwipeRefreshLayout?
    abstract val recyclerView: RecyclerView?


    open fun getLayoutManager(): RecyclerView.LayoutManager =
        LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLoadRefresh()
    }

    open fun setUpLoadRefresh() {
        with(pagerAdapter) {
            swipeRefreshLayout?.setOnRefreshListener { refresh() }
            recyclerView?.adapter = withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter(this),
                footer = PagingLoadStateAdapter(this)
            )

            with(viewModel) {
                launchOnLifecycleScope {
                    itemList?.collectLatest {
                        submitData(it)
                    }
                }
                launchOnLifecycleScope {
                    loadStateFlow.collectLatest {
                        swipeRefreshLayout?.isRefreshing = it.refresh is LoadState.Loading
                        viewModel.isRefreshing.value = it.refresh is LoadState.Loading
                    }
                }
                launchOnLifecycleScope {
                    loadStateFlow.distinctUntilChangedBy { it.refresh }
                        .filter { it.refresh is LoadState.NotLoading }
                        .collect {
                            recyclerView?.scrollToPosition(0)
                        }
                }
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView?.adapter = null
    }

}