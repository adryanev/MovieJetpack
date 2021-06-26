package dev.adryanev.dicoding.moviejetpack.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import dev.adryanev.dicoding.moviejetpack.R
import dev.adryanev.dicoding.moviejetpack.ui.favorite.movie.FavoriteMovieFragment
import dev.adryanev.dicoding.moviejetpack.ui.favorite.tvshow.FavoriteTvShowFragment
import dev.adryanev.dicoding.moviejetpack.ui.home.movies.MovieFragment
import dev.adryanev.dicoding.moviejetpack.ui.home.tvshows.TvShowFragment

class SectionPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    companion object {
        @StringRes
        val TAB_TITLES = intArrayOf(R.string.movie_fav, R.string.tv_fav)
        const val NUM_PAGES = 2
    }

//    override fun getPageTitle(position: Int): CharSequence =
//        context.resources.getString(TAB_TITLES[position])

    override fun getItemCount(): Int = NUM_PAGES

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }


}