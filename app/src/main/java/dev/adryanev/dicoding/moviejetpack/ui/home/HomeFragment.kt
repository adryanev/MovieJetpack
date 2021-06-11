package dev.adryanev.dicoding.moviejetpack.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentHomeBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, HomeViewModel>() {

    override val layoutId: Int
        get() = R.layout.fragment_home

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment))
        viewBinding.toolbarHome.setupWithNavController(navController, appBarConfiguration)

        val sectionPagerAdapter = SectionPagerAdapter(this)
        viewBinding.viewPager.adapter = sectionPagerAdapter

        TabLayoutMediator(viewBinding.tab, viewBinding.viewPager) { tab, position ->
            tab.text = context?.resources?.getString(SectionPagerAdapter.TAB_TITLES[position])

        }.attach()

    }

    override val viewModel: HomeViewModel by viewModels()

}