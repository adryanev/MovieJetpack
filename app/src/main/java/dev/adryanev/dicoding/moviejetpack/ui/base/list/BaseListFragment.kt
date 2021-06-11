package dev.adryanev.dicoding.moviejetpack.ui.base.list

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseFragment

abstract class BaseListFragment<ViewBinding: ViewDataBinding, ViewModel: BaseListViewModel<Item>, Item: Any> : BaseFragment<ViewBinding, ViewModel>() {

    abstract val listAdapter: BaseListAdapter<Item, out ViewDataBinding>
    abstract val swipeRefreshLayout: SwipeRefreshLayout?
    abstract val recyclerView: RecyclerView?


    open fun getLayoutManager(): RecyclerView.LayoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpLoadRefresh()
    }

    open fun setUpLoadRefresh() {
        swipeRefreshLayout?.setOnRefreshListener {
            viewModel.doRefresh()
        }

        recyclerView?.layoutManager = getLayoutManager()
        recyclerView?.adapter = listAdapter
        recyclerView?.setHasFixedSize(true)

        viewModel.apply {
            itemList.observe(viewLifecycleOwner, {
                listAdapter.submitList(it)
            })
            firstLoad()
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        recyclerView?.adapter = null
    }

}