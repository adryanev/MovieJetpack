package dev.adryanev.dicoding.moviejetpack.ui.favorite.movie

import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentFavoriteMovieBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.getNavController
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagedFragment
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagingAdapter
import dev.adryanev.dicoding.moviejetpack.ui.favorite.FavoriteAdapter

@AndroidEntryPoint
class FavoriteMovieFragment :
    BasePagedFragment<FragmentFavoriteMovieBinding, FavoriteMovieViewModel, FavoriteAndMovie>() {

    override val viewModel: FavoriteMovieViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_favorite_movie
    override val pagerAdapter: BasePagingAdapter<FavoriteAndMovie, out ViewDataBinding> by lazy {
        FavoriteAdapter(itemClickListener = { toMovieDetail(it.movie) })
    }

    private fun toMovieDetail(movie: MovieUi) {
        getNavController()?.navigate(FavoriteMovieFragmentDirections.toMovieDetail(movie))

    }

    override val swipeRefreshLayout: SwipeRefreshLayout
        get() = viewBinding.refreshFavorite
    override val recyclerView: RecyclerView
        get() = viewBinding.rvFavorite

}