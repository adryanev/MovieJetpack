package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import androidx.core.app.ShareCompat
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentTvShowBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BaseListAdapter
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BaseListFragment

@AndroidEntryPoint
class TvShowFragment : BaseListFragment<FragmentTvShowBinding, TvShowViewModel, TvShow>() {



    override val listAdapter: BaseListAdapter<TvShow, out ViewDataBinding>
       by lazy {  TvShowListAdapater(itemCLickListener = { toDetail(it) })}

    private fun toDetail(tvShow: TvShow) {

    }

    override val swipeRefreshLayout: SwipeRefreshLayout
        get() = viewBinding.refreshTvshow
    override val recyclerView: RecyclerView
        get() = viewBinding.rvTvshow
    override val viewModel: TvShowViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_tv_show

}