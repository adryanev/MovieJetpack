package dev.adryanev.dicoding.moviejetpack.ui.detail

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
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieEntity
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentDetailBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseFragment

@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding, DetailViewModel>() {

    override val viewModel: DetailViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_detail

    private val args: DetailFragmentArgs by navArgs()

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
    }

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        detailViewModel = ViewModelProvider(
//            this,
//            ViewModelProvider.NewInstanceFactory()
//        )[DetailViewModel::class.java]
//
//        val extras = intent.extras
//        if (extras != null) {
//            val movie : MovieEntity? = extras.getParcelable(EXTRA_MOVIE)
//            val isMovie: Boolean = extras.getBoolean(IS_MOVIE,false)
//            if (movie != null) {
//                detailViewModel.setSelectedMovie(movie,isMovie)
//            }
//        }
//
//        activityDetailBinding = DataBindingUtil.setContentView(this, R.layout.fragment_detail)
//        activityDetailBinding.viewModel = detailViewModel
//        activityDetailBinding.itemDetail.viewModel = detailViewModel
//
//        setSupportActionBar(activityDetailBinding.toolbarDetail)
//        supportActionBar?.apply {
//            title = ""
//            setDisplayShowHomeEnabled(true)
//            setDisplayHomeAsUpEnabled(true)
//        }
//        activityDetailBinding.fabDetail.setOnClickListener{
//            onShareClick(detailViewModel.movie!!)
//        }
//
//    }
//    override fun onSupportNavigateUp(): Boolean {
//        onBackPressed()
//        return true
//    }

    fun onShareClick(movie: MovieEntity) {
            val mimeType = "text/plain"
            ShareCompat.IntentBuilder(requireContext())
                .setType(mimeType)
                .setChooserTitle(getString(R.string.share_title))
                .setText(resources.getString(R.string.share_text, movie.title))
                .startChooser()
    }


}