package dev.adryanev.dicoding.moviejetpack.data.local.preference

import android.content.SharedPreferences
import androidx.core.content.edit
import javax.inject.Inject

class AppPrefences @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : Preference {
    companion object {
        private const val FIRST_RUN = "first_run"
    }

    override fun isFirstRun(): Boolean {
        val isFirstRun = sharedPreferences.getBoolean(FIRST_RUN, true)
        if (isFirstRun) {
            sharedPreferences.edit { putBoolean(FIRST_RUN, false) }
        }
        return isFirstRun
    }

    override fun remove(key: String) {
        sharedPreferences.edit {
            remove(key)
        }
    }

    override fun clear() {
        sharedPreferences.edit { clear() }
    }
}