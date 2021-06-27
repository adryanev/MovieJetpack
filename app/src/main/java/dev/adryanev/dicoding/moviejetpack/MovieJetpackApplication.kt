package dev.adryanev.dicoding.moviejetpack

import android.app.Application
import com.facebook.stetho.Stetho
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import timber.log.Timber
import timber.log.Timber.DebugTree

@HiltAndroidApp
class MovieJetpackApplication : Application() {

    val appScope by lazy {
        CoroutineScope(SupervisorJob())
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(DebugTree())
        }

        Stetho.initializeWithDefaults(this)

    }
}