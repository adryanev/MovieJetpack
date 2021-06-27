package dev.adryanev.dicoding.moviejetpack.data.paging.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.adryanev.dicoding.moviejetpack.data.entities.DataResult
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.mapper.toMovieUI
import dev.adryanev.dicoding.moviejetpack.data.remote.sources.MovieRemoteDataSource
import javax.inject.Inject

class MoviePagingDataSource @Inject constructor(private val remoteDataSource: MovieRemoteDataSource) :
    PagingSource<Int, MovieUi>() {
    override fun getRefreshKey(state: PagingState<Int, MovieUi>): Int = INITIAL_PAGE_INDEX

    private companion object {
        const val INITIAL_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieUi> {
        val pageNumber = params.key ?: INITIAL_PAGE_INDEX

        when (val movieList = remoteDataSource.getMovieList(pageNumber)) {
            is DataResult.Success -> return LoadResult.Page(
                data = movieList.data.results!!.map { it.toMovieUI() },
                prevKey = if (pageNumber == INITIAL_PAGE_INDEX) null else pageNumber - 1,
                nextKey = if (movieList.data.results.isNullOrEmpty()) null else pageNumber + 1,

                )
            is DataResult.Error -> {
                return LoadResult.Error(movieList.e)
            }
        }

    }

}