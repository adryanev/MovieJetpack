package dev.adryanev.dicoding.moviejetpack.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ConnectivityChecker @Inject constructor(@ApplicationContext context: Context) {
    private val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    fun isNetworkActive(): Boolean {
        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            connectivityManager.isDefaultNetworkActive
        } else {
            var hasInternet = false
            connectivityManager.allNetworks.also { networks ->
                if (networks.isNotEmpty()) {
                    networks.forEach { network ->
                        connectivityManager.getNetworkCapabilities(network)?.let {
                            if (it.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET))
                                hasInternet = true
                        }
                    }
                }
            }
            return hasInternet
        }
    }
}