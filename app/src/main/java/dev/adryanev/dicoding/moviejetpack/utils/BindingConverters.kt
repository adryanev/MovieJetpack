package dev.adryanev.dicoding.moviejetpack.utils

import android.view.View
import androidx.databinding.BindingConversion

object ConverterUtil {
    @JvmStatic
    fun isNull(attributeName: Any?): Boolean {
        return attributeName == null
    }
}

object BindingConverters {
    @BindingConversion
    @JvmStatic
    fun booleanToVisibility(isNotVisible: Boolean): Int {
        return if (isNotVisible) View.GONE else View.VISIBLE
    }
}
