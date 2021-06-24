package dev.adryanev.dicoding.moviejetpack.ui.detailmovie

import android.os.Bundle
import android.view.View
import androidx.core.app.ShareCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentDetailMovieBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseFragment

@AndroidEntryPoint
class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding, DetailMovieViewModel>() {

    override val viewModel: DetailMovieViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_detail_movie

    private val args: DetailMovieFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        viewBinding.toolbarDetail.setupWithNavController(navController, appBarConfiguration)


        viewBinding.toolbarDetail.apply {
            title = ""

        }
        viewModel.apply {
            args.movie.let {
                movie.value = it
            }
            viewBinding.itemDetail.viewModel = this
        }

        viewBinding.fabDetail.setOnClickListener {
            onShareClick(args.movie)
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


}