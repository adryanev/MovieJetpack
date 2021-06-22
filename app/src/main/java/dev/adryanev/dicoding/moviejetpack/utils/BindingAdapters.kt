package dev.adryanev.dicoding.moviejetpack.utils

import android.content.res.ColorStateList
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.View
import android.webkit.WebView
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.widget.ContentLoadingProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.signature.ObjectKey
import com.google.android.material.textview.MaterialTextView
import dev.adryanev.dicoding.moviejetpack.BuildConfig
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.constants.Constants
import java.text.NumberFormat
import java.util.*

object BindingAdapters {

    @BindingAdapter("genreToString")
    @JvmStatic fun setGenreToString(view: TextView, genre: List<Int>? ){
        val genreString =Genre.getGenre(genre)
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

    @BindingAdapter("onScrollListener")
    @JvmStatic fun RecyclerView.customScrollListener(listener: RecyclerView.OnScrollListener?) {
        if (listener != null) addOnScrollListener(listener)
    }

    @BindingAdapter("glideSrc")
    @JvmStatic fun ImageView.setGlideSrc(@DrawableRes src: Int?) {
        Glide.with(context).load(src).into(this)
    }

    @BindingAdapter("loadUri")
    @JvmStatic  fun ImageView.loadLocalImage(uri: Uri?) {
        Glide.with(context).load(uri).into(this)
    }

    @BindingAdapter(value = ["loadImageLocal"])
    @JvmStatic  fun ImageView.loadLocalImage(imageName: String?) {
        if (imageName.isNullOrBlank().not()) {
            Glide.with(context)
                .load(resources.getIdentifier(imageName, "drawable", BuildConfig.APPLICATION_ID))
                .into(this)
        }
    }

    @BindingAdapter(value = ["isLoading"])
    @JvmStatic fun ContentLoadingProgressBar.show(isLoading: Boolean?) {
        if (isLoading == true) show() else hide()
    }


    @BindingAdapter(
        value = [
            "imageUrl",
            "thumbnailUrl",
            "placeholder",
            "errorDrawable",
            "requestListener",
            "centerCrop",
            "fitCenter",
            "circleCrop",
            "skipMemoryCache",
            "diskCacheStrategy",
            "signatureKey"
        ],
        requireAll = false
    )
    @JvmStatic fun ImageView.loadImage(
        imageUrl: String? = null,
        thumbnailUrl: String? = null,
        placeHolderDrawable: Drawable? = null,
        errorDrawable: Drawable? = null,
        requestListener: RequestListener<Drawable>? = null,
        centerCrop: Boolean = false,
        fitCenter: Boolean = false,
        circleCrop: Boolean = false,
        skipMemoryCache: Boolean? = null,
        diskCacheStrategy: DiskCacheStrategy? = null,
        signatureKey: String? = null
    ) {
        if (imageUrl.isNullOrBlank()) {
            if (placeHolderDrawable != null) setImageDrawable(placeHolderDrawable)
            return
        }

        val requestOptions = RequestOptions().apply {
            if (skipMemoryCache != null) skipMemoryCache(skipMemoryCache)
            diskCacheStrategy(diskCacheStrategy ?: DiskCacheStrategy.RESOURCE)

            if (placeHolderDrawable != null) placeholder(placeHolderDrawable)
            if (errorDrawable != null) error(errorDrawable)

            // crop type
            if (centerCrop) centerCrop()
            if (fitCenter) fitCenter()
            if (circleCrop) circleCrop()

            if (!signatureKey.isNullOrBlank()) {
                signature(ObjectKey(signatureKey))
            }
        }

        Glide.with(context).load(imageUrl).apply {
            transition(DrawableTransitionOptions.withCrossFade())
            addListener(requestListener)
            apply(requestOptions)
        }.into(this)
    }

    @BindingAdapter("clickSafe")
    @JvmStatic fun View.setClickSafe(listener: View.OnClickListener?) {
        setOnClickListener(object : View.OnClickListener {
            var lastClickTime: Long = 0

            override fun onClick(v: View) {
                if (System.currentTimeMillis() - lastClickTime < Constants.THRESHOLD_CLICK_TIME) return
                listener?.onClick(v)
                lastClickTime = System.currentTimeMillis()
            }
        })
    }

    @BindingAdapter("onSingleClick")
    @JvmStatic   fun View.setSingleClick(execution: () -> Unit) {
        setOnClickListener(object : View.OnClickListener {
            var lastClickTime: Long = 0

            override fun onClick(p0: View?) {
                if (System.currentTimeMillis() - lastClickTime < Constants.THRESHOLD_CLICK_TIME) return
                lastClickTime = System.currentTimeMillis()
                execution.invoke()
            }
        })
    }

    @BindingAdapter("backgroundTint")
    @JvmStatic fun TextView.customBackgroundTint(color: Int?) {
        if (color == null) return
        background?.setTint(color)
    }

    @BindingAdapter("tint")
    @JvmStatic fun ImageView.customTint(color: Int?) {
        if (color == null) return
        imageTintList = ColorStateList.valueOf(color)
    }

    @BindingAdapter("enableRefresh")
    @JvmStatic fun SwipeRefreshLayout.enableRefresh(enable: Boolean?) {
        isEnabled = enable == true
    }

}