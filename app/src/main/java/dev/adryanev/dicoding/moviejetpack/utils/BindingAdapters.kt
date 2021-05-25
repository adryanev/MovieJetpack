package dev.adryanev.dicoding.moviejetpack.utils

import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import dev.adryanev.dicoding.moviejetpack.R
import java.text.NumberFormat
import java.util.*

object BindingAdapters {

    @BindingAdapter("genreToString")
    @JvmStatic fun setGenreToString(view: TextView, genre: Array<String> ){
        val genreString = genre.joinToString(", ")
        view.text = genreString
    }

    @BindingAdapter("images")
    @JvmStatic fun setImages(view: ImageView, image:String){
        val posterId: Int = view.resources.getIdentifier(
            image,
            "drawable",
            view.context.packageName
        )
        Glide.with(view.context)
            .load(ContextCompat.getDrawable(view.context, posterId))
            .apply(
                RequestOptions.placeholderOf(R.drawable.ic_loading)
                    .error(R.drawable.ic_error)
            )
            .into(view)
    }
    @BindingAdapter("currency")
    @JvmStatic fun setCurrency(view: TextView, amount:Int?){
        if(amount!=null){
            val format: NumberFormat = NumberFormat.getCurrencyInstance()
            format.maximumFractionDigits = 0
            format.currency = Currency.getInstance("USD")
            view.text = format.format(amount)
        }

    }
}