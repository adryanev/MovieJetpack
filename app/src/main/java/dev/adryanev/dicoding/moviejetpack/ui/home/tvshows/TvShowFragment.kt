package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentTvShowBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.getNavController
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BaseListFragment
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagingAdapter
import dev.adryanev.dicoding.moviejetpack.ui.home.movies.MovieFragmentDirections

@AndroidEntryPoint
class TvShowFragment : BaseListFragment<FragmentTvShowBinding, TvShowViewModel, TvShow>() {


    private fun toDetail(tvShow: TvShow) {
        getNavController()?.navigate(MovieFragmentDirections.actionGlobalDetailTvShowFragment(tvShow))

    }

    override val swipeRefreshLayout: SwipeRefreshLayout
        get() = viewBinding.refreshTvshow
    override val recyclerView: RecyclerView
        get() = viewBinding.rvTvshow
    override val viewModel: TvShowViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_tv_show
    override val pagerAdapter: BasePagingAdapter<TvShow, out ViewDataBinding> by lazy {
        TvShowListAdapater(itemCLickListener = { toDetail(it) })
    }

}