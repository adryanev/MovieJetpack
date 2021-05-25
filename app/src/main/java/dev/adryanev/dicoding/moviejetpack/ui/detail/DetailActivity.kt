package dev.adryanev.dicoding.moviejetpack.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ShareCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity
import dev.adryanev.dicoding.moviejetpack.databinding.ActivityDetailBinding
import dev.adryanev.dicoding.moviejetpack.ui.home.MovieCallback
import timber.log.Timber

class DetailActivity : AppCompatActivity(),MovieCallback {
    private lateinit var activityDetailBinding: ActivityDetailBinding
    private lateinit var detailViewModel: DetailViewModel

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val IS_MOVIE = "is_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movie : MovieEntity? = extras.getParcelable(EXTRA_MOVIE)
            val isMovie: Boolean = extras.getBoolean(IS_MOVIE,false)
            if (movie != null) {
                detailViewModel.setSelectedMovie(movie,isMovie)
            }
        }

        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_detail)
        activityDetailBinding.vm = detailViewModel
        activityDetailBinding.itemDetail.vm = detailViewModel

        setSupportActionBar(activityDetailBinding.toolbarDetail)
        supportActionBar?.apply {
            title = ""
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        activityDetailBinding.fabDetail.setOnClickListener{
            onShareClick(detailViewModel.movie!!)
        }

    }
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onShareClick(movie: MovieEntity) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder
                .from(this)
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_title))
                .setText(resources.getString(R.string.share_text, movie.title))
                .startChooser()
    }

    override fun onItemClick(movie: MovieEntity) {
        return
    }
}