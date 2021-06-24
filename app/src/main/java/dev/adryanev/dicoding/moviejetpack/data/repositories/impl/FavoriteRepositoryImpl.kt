package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.paging.datasource.FavoriteMoviePagingDataSource
import dev.adryanev.dicoding.moviejetpack.data.paging.datasource.FavoriteTvShowPagingDataSource
import dev.adryanev.dicoding.moviejetpack.data.repositories.FavoriteRepository
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(val localDataSource: LocalDataSource) :
    FavoriteRepository {

    private val pagingConfig = PagingConfig(10)

    override suspend fun getFavoriteMovies() =
        Pager(pagingConfig) { FavoriteMoviePagingDataSource(localDataSource = localDataSource) }.flow

    override suspend fun getFavoriteTvShows() =
        Pager(pagingConfig) { FavoriteTvShowPagingDataSource(localDataSource) }.flow
}