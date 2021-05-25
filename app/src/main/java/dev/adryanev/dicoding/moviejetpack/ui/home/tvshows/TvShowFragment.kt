package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity
import dev.adryanev.dicoding.moviejetpack.databinding.TvShowFragmentBinding
import dev.adryanev.dicoding.moviejetpack.ui.detail.DetailActivity
import dev.adryanev.dicoding.moviejetpack.ui.home.movies.MovieAdapter
import dev.adryanev.dicoding.moviejetpack.ui.home.MovieCallback
import timber.log.Timber

class TvShowFragment : Fragment(), MovieCallback {

    private lateinit var viewModel: TvShowViewModel
    private lateinit var tvShowFragmentBinding: TvShowFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        tvShowFragmentBinding = TvShowFragmentBinding.inflate(inflater, container, false)
        return tvShowFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]
            val movies = viewModel.getTvShows()
            val movieAdapter = MovieAdapter(this)
            movieAdapter.setMovies(movies)
            with(tvShowFragmentBinding.rvTvshow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = movieAdapter
            }
        }
        Timber.d("created")

    }

    override fun onShareClick(movie: MovieEntity) {
        if (activity != null) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_title))
                .setText(resources.getString(R.string.share_text, movie.title))
                .startChooser()
        }
    }

    override fun onItemClick(movie: MovieEntity) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_MOVIE, movie)
        intent.putExtra(DetailActivity.IS_MOVIE, false)
        context?.startActivity(intent)
    }

}