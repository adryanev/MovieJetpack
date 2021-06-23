package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentMovieBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.getNavController
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BaseMediatorPagedFragment
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagingAdapter

@AndroidEntryPoint
class MovieFragment : BaseMediatorPagedFragment<FragmentMovieBinding, MovieViewModel, Movie>() {

    override val viewModel: MovieViewModel by viewModels()
    override val pagerAdapter: BasePagingAdapter<Movie, out ViewDataBinding> by lazy {
        MoviePagingAdapter(
            itemClickListener = { toMovieDetail(it) }
        )
    }

    private fun toMovieDetail(movie: Movie) {
        getNavController()?.navigate(MovieFragmentDirections.actionGlobalDetailFragment(movie))

    }

    override val swipeRefreshLayout: SwipeRefreshLayout
        get() = viewBinding.refreshMovie
    override val recyclerView: RecyclerView
        get() = viewBinding.rvMovie
    override val layoutId: Int
        get() = R.layout.fragment_movie


}