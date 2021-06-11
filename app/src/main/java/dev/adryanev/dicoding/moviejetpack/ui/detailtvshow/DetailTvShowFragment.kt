package dev.adryanev.dicoding.moviejetpack.ui.detailtvshow

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
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentDetailTvshowBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseFragment

@AndroidEntryPoint
class DetailTvShowFragment : BaseFragment<FragmentDetailTvshowBinding, DetailTvShowViewModel>() {

    override val viewModel: DetailTvShowViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_detail_tvshow

    private val args: DetailTvShowFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        viewBinding.toolbarDetail.setupWithNavController(navController, appBarConfiguration)

        viewBinding.toolbarDetail.apply {
            title = ""

        }
        viewModel.apply {
            args.tvShow.let {
                tvShow.value = it
            }
            viewBinding.itemDetailTvshow.viewModel = this
        }

        viewBinding.fabDetail.setOnClickListener {
            onShareClick(args.tvShow)
        }
    }

    fun onShareClick(tvShow: TvShow) {
        val mimeType = "text/plain"
        ShareCompat.IntentBuilder(requireContext())
            .setType(mimeType)
            .setChooserTitle(getString(R.string.share_title))
            .setText(resources.getString(R.string.share_text, tvShow.originalName))
            .startChooser()
    }


}