package dev.adryanev.dicoding.moviejetpack.ui.favorite

import androidx.recyclerview.widget.DiffUtil
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.databinding.ItemFavoriteBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagingAdapter
import dev.adryanev.dicoding.moviejetpack.utils.BindingAdapters.setSingleClick

class FavoriteAdapter(val itemClickListener: (FavoriteAndMovie) -> Unit = {}) :
    BasePagingAdapter<FavoriteAndMovie, ItemFavoriteBinding>(object :
        DiffUtil.ItemCallback<FavoriteAndMovie>() {
        override fun areItemsTheSame(
            oldItem: FavoriteAndMovie,
            newItem: FavoriteAndMovie
        ): Boolean {
            return oldItem.favorite?.favoriteId == newItem.favorite?.favoriteId
        }

        override fun areContentsTheSame(
            oldItem: FavoriteAndMovie,
            newItem: FavoriteAndMovie
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