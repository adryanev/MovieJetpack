package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentMovieBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.getNavController
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BaseMediatorPagedFragment
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagingAdapter
import dev.adryanev.dicoding.moviejetpack.ui.home.movies.MovieFragmentDirections
import dev.adryanev.dicoding.moviejetpack.ui.home.movies.MoviePagingAdapter

@AndroidEntryPoint
class TvShowFragment :
    BaseMediatorPagedFragment<FragmentMovieBinding, TvShowViewModel, MovieUi>() {


    private fun toDetail(movie: MovieUi) {
        getNavController()?.navigate(TvShowFragmentDirections.toMovieDetail(movie))

    }

    override val swipeRefreshLayout: SwipeRefreshLayout
        get() = viewBinding.refreshMovie
    override val recyclerView: RecyclerView
        get() = viewBinding.rvMovie
    override val viewModel: TvShowViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_movie
    override val pagerAdapter: BasePagingAdapter<MovieUi, out ViewDataBinding> by lazy {
        MoviePagingAdapter(itemClickListener = { toDetail(it) })
    }

}