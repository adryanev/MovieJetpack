package dev.adryanev.dicoding.moviejetpack.data.paging.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import timber.log.Timber
import javax.inject.Inject

class FavoriteMoviePagingDataSource @Inject constructor(private val localDataSource: LocalDataSource): PagingSource<Int, FavoriteAndMovie>() {
    override fun getRefreshKey(state: PagingState<Int, FavoriteAndMovie>): Int? = state.anchorPosition
    private companion object {
        const val INITIAL_PAGE_INDEX = 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteAndMovie> {
       val pageNumber = params.key ?: INITIAL_PAGE_INDEX
       val favoriteList = listOf<FavoriteAndMovie>()
        Timber.d("$pageNumber")
        return LoadResult.Page(
            data = favoriteList,
            prevKey = if(pageNumber == INITIAL_PAGE_INDEX) null else pageNumber - 1,
            nextKey = if(favoriteList.isNullOrEmpty()) null else pageNumber + 1,

        )
    }

}