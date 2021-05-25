package dev.adryanev.dicoding.moviejetpack

import android.app.Application
import timber.log.Timber

import timber.log.Timber.DebugTree
class MovieJetpackApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }
    }
}