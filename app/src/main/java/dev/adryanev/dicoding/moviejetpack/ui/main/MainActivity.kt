package dev.adryanev.dicoding.moviejetpack.ui.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.databinding.ActivityMainBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseActivity
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by viewModels()
    override val layoutId: Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navView: BottomNavigationView = viewBinding.bottomNav

//        val navHostFragment = supportFragmentManager
//            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment?

//        val navController = navHostFragment?.navController
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfiguration = AppBarConfiguration(
            navController.graph
        )
        navController.let {
            setupActionBarWithNavController(it, appBarConfiguration)
            navView.setupWithNavController(it)
            it.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.detailMovieFragment -> {
                        navView.visibility = View.GONE
                    }
                    R.id.splashFragment -> {
                        navView.visibility = View.GONE
                        supportActionBar?.hide()

                    }
                    else -> {
                        navView.visibility = View.VISIBLE
                        supportActionBar?.show()

                    }
                }
            }
        }

    }
}