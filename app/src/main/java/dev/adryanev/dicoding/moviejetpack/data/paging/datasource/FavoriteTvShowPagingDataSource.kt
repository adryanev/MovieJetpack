package dev.adryanev.dicoding.moviejetpack.data.paging.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import javax.inject.Inject

class FavoriteTvShowPagingDataSource @Inject constructor(private val localDataSource: LocalDataSource) :
    PagingSource<Int, FavoriteAndMovie>() {
    override fun getRefreshKey(state: PagingState<Int, FavoriteAndMovie>): Int? =
        state.anchorPosition

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FavoriteAndMovie> {
        val pageNumber = params.key ?: INITIAL_PAGE_INDEX
        val favoriteList = localDataSource.getAllFavoriteTvShow()
        return LoadResult.Page(
            data = favoriteList,
            prevKey = if (pageNumber == INITIAL_PAGE_INDEX) null else pageNumber - 1,
            nextKey = if (favoriteList.isNullOrEmpty()) null else pageNumber + 1,

            )
    }

}