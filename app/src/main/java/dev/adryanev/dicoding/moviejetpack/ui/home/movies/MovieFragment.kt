package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentMovieBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.getNavController
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BaseMediatorPagedFragment
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagingAdapter

@AndroidEntryPoint
class MovieFragment : BaseMediatorPagedFragment<FragmentMovieBinding, MovieViewModel, MovieUi>() {

    override val viewModel: MovieViewModel by viewModels()
    override val pagerAdapter: BasePagingAdapter<MovieUi, out ViewDataBinding> by lazy {
        MoviePagingAdapter(
            itemClickListener = { toMovieDetail(it) }
        )
    }

    private fun toMovieDetail(movie: MovieUi) {
        getNavController()?.navigate(MovieFragmentDirections.toMovieDetail(movie))

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.movieFragment, R.id.tvShowFragment, R.id.favoriteFragment))
        viewBinding.toolbarMovie.setupWithNavController(findNavController(), appBarConfiguration)
    }
    override val swipeRefreshLayout: SwipeRefreshLayout
        get() = viewBinding.refreshMovie
    override val recyclerView: RecyclerView
        get() = viewBinding.rvMovie
    override val layoutId: Int
        get() = R.layout.fragment_movie


}