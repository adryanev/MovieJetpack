package dev.adryanev.dicoding.moviejetpack.ui.home.tvshows

import androidx.recyclerview.widget.DiffUtil
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.databinding.ItemTvshowBinding
import dev.adryanev.dicoding.moviejetpack.ui.base.list.BasePagingAdapter
import dev.adryanev.dicoding.moviejetpack.utils.BindingAdapters.setSingleClick

class TvShowListAdapater(val itemCLickListener: (TvShow) -> Unit = {}) :
    BasePagingAdapter<TvShow, ItemTvshowBinding>(object : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean =
            oldItem == newItem
    }) {
    override fun getLayoutRes(viewType: Int): Int = R.layout.item_tvshow

    override fun bindFirstTime(binding: ItemTvshowBinding) {
        binding.apply {
            root.setSingleClick {
                item?.apply {
                    itemCLickListener(this)
                }
            }
        }
    }
}