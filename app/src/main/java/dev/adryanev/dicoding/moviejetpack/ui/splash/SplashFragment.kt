package dev.adryanev.dicoding.moviejetpack.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.databinding.FragmentSplashBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseFragment
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel
import dev.adryanev.dicoding.moviejetpack.ui.base.getNavController
import dev.adryanev.dicoding.moviejetpack.utils.EspressoIdlingResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by viewModels()
    override val layoutId: Int = R.layout.fragment_splash

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            EspressoIdlingResource.increment()
            delay(2000)
            EspressoIdlingResource.decrement()
            navigateToMain()

        }
    }

    private fun navigateToMain() {
        getNavController()?.navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
    }
}