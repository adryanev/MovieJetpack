package dev.adryanev.dicoding.moviejetpack.ui.home.movies

import androidx.recyclerview.widget.DiffUtil
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.databinding.ItemMovieBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagingAdapter
import dev.adryanev.dicoding.moviejetpack.utils.BindingAdapters.setSingleClick

class MoviePagingAdapter(val itemClickListener: (MovieUi) -> Unit = {}) :
    BasePagingAdapter<MovieUi, ItemMovieBinding>(object : DiffUtil.ItemCallback<MovieUi>() {
        override fun areItemsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUi, newItem: MovieUi): Boolean {
            return oldItem == newItem
        }

    }) {
    override fun getLayoutRes(viewType: Int): Int {
        return R.layout.item_movie
    }

    override fun bindFirstTime(binding: ItemMovieBinding) {
        binding.apply {
            root.setSingleClick {
                item?.apply {
                    itemClickListener(this)
                }
            }
        }
    }

}