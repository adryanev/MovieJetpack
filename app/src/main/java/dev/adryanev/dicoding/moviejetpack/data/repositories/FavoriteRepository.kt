package dev.adryanev.dicoding.moviejetpack.data.repositories

import androidx.paging.PagingData
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.MovieUiAndFavorite
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun getFavoriteMovies(): Flow<PagingData<MovieUiAndFavorite>>

    suspend fun getFavoriteTvShows(): Flow<PagingData<MovieUiAndFavorite>>
}