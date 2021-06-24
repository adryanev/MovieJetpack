package dev.adryanev.dicoding.moviejetpack.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.databinding.ActivityMainBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseActivity
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by viewModels()
    override val layoutId: Int = R.layout.activity_main

    val navView: BottomNavigationView by lazy { viewBinding.bottomNav }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

        navHostFragment?.navController?.let {
            navView.setupWithNavController(it)
            it.addOnDestinationChangedListener{ _, destination, _ ->
                if (destination.id == R.id.detailMovieFragment || destination.id == R.id.splashFragment) {
                    navView.visibility = View.GONE
                } else {
                    navView.visibility = View.VISIBLE
                }
            }
        }

    }
}