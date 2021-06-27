package dev.adryanev.dicoding.moviejetpack.ui.detailmovie

import android.os.Bundle
import android.view.*
import androidx.core.app.ShareCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentDetailMovieBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseFragment
import timber.log.Timber

@AndroidEntryPoint
class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding, DetailMovieViewModel>() {

    override val viewModel: DetailMovieViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_detail_movie

    var menu: Menu? = null;
    private val args: DetailMovieFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)

        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.apply {
            viewBinding.itemDetail.viewModel = this
        }
        viewBinding.fabDetail.setOnClickListener {
            onShareClick(args.movie)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.apply {
            args.movie.let {
                movie.value = it

                lifecycleScope.launchWhenCreated {
                    getMovieFavorite(it.id!!)
                }

            }

        }
    }

    fun onShareClick(movie: MovieUi) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder(requireContext())
            .setType(mimeType)
            .setChooserTitle(getString(R.string.share_title))
            .setText(resources.getString(R.string.share_text, movie.title))
            .startChooser()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        this.menu = menu
        activity?.menuInflater?.inflate(R.menu.appbar_menu, menu)
        viewModel.favorite.observe(viewLifecycleOwner, {
            setBookmarkState(it != null)
        })

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favourite) {

            viewModel.setBookmark(
                viewModel.favorite.value ?: FavoriteAndMovie(
                    favorite =Favorite(
                        movieId = viewModel.movie.value?.id!!,
                        movieType = viewModel.movie.value?.type
                    ),
                    movie = viewModel.movie.value!!,

                ),
                viewModel.favorite.value == null
            )
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favourite)
        if (state) {
            menuItem?.icon =
                activity?.let { ContextCompat.getDrawable(it, R.drawable.ic_baseline_favorite_24) }
        } else {
            menuItem?.icon =
                activity?.let {
                    ContextCompat.getDrawable(
                        it,
                        R.drawable.ic_baseline_favorite_border_24
                    )
                }
        }
    }


}