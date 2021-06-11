package dev.adryanev.dicoding.moviejetpack.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.databinding.ActivityMainBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseActivity
import dev.adryanev.dicoding.moviejetpack.ui.base.BaseViewModel

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, BaseViewModel>() {

    override val viewModel: BaseViewModel by viewModels()
    override val layoutId: Int = R.layout.activity_main
}