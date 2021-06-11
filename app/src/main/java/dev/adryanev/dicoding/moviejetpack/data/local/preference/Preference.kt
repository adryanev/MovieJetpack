package dev.adryanev.dicoding.moviejetpack.data.local.preference

interface Preference {
    fun isFirstRun(): Boolean

    fun remove(key: String)

    fun clear()
}