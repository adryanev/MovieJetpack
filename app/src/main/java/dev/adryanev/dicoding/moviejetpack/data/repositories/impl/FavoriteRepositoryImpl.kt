package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.paging.datasource.FavoriteMoviePagingDataSource
import dev.adryanev.dicoding.moviejetpack.data.paging.datasource.FavoriteTvShowPagingDataSource
import dev.adryanev.dicoding.moviejetpack.data.repositories.FavoriteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FavoriteRepositoryImpl @Inject constructor(val localDataSource: LocalDataSource) :
    FavoriteRepository {

    private val pagingConfig = PagingConfig(10)

    override suspend fun getFavoriteMovies() =
        Pager(pagingConfig) { localDataSource.getAllFavoriteMovie() }.flow

    override suspend fun getFavoriteTvShows() =
        Pager(pagingConfig,pagingSourceFactory = {localDataSource.getAllFavoriteTvShow()}).flow

    override suspend fun setMovieFavorite(movie: FavoriteAndMovie, state: Boolean) {
        localDataSource.setMovieFavorite(movie, state)
    }

    override suspend fun getFavoriteMovieById(id: Int): Flow<FavoriteAndMovie> {
        return localDataSource.getFavoriteMovieById(id)
    }



}