package dev.adryanev.dicoding.moviejetpack.ui.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentFavoriteBinding
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentHomeBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseFragment
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel
import dev.adryanev.dicoding.moviejetpack.ui.home.HomeViewModel
import dev.adryanev.dicoding.moviejetpack.ui.home.SectionPagerAdapter
@AndroidEntryPoint
class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, BaseViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_favorite
    override val viewModel: BaseViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        viewBinding.viewPager.adapter = sectionPagerAdapter

        TabLayoutMediator(viewBinding.tab, viewBinding.viewPager) { tab, position ->
            tab.text = context?.resources?.getString(SectionPagerAdapter.TAB_TITLES[position])

        }.attach()

    }

}