package dev.adryanev.dicoding.moviejetpack.ui.favorite

import androidx.recyclerview.widget.DiffUtil
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import dev.adryanev.dicoding.moviejetpack.databinding.ItemFavoriteBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagingAdapter
import dev.adryanev.dicoding.moviejetpack.utils.BindingAdapters.setSingleClick

class FavoriteAdapter(val itemClickListener: (MovieUiAndFavorite) -> Unit = {}) :
    BasePagingAdapter<MovieUiAndFavorite, ItemFavoriteBinding>(object :
        DiffUtil.ItemCallback<MovieUiAndFavorite>() {
        override fun areItemsTheSame(
            oldItem: MovieUiAndFavorite,
            newItem: MovieUiAndFavorite
        ): Boolean {
            return oldItem.favorite.id == newItem.favorite.id
        }

        override fun areContentsTheSame(
            oldItem: MovieUiAndFavorite,
            newItem: MovieUiAndFavorite
        ): Boolean {
            return oldItem.favorite == newItem.favorite
        }

    }) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_favorite
    override fun bindFirstTime(binding: ItemFavoriteBinding) {
        binding.apply {
            root.setSingleClick {
                item?.apply {
                    itemClickListener(this)
                }
            }
        }
    }
}