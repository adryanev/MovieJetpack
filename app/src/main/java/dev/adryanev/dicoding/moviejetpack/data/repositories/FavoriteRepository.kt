package dev.adryanev.dicoding.moviejetpack.data.repositories

import androidx.paging.PagingData
import dev.adryanev.dicoding.moviejetpack.data.entities.relations.FavoriteAndMovie
import kotlinx.coroutines.flow.Flow

interface FavoriteRepository {

    suspend fun getFavoriteMovies(): Flow<PagingData<FavoriteAndMovie>>

    suspend fun getFavoriteTvShows(): Flow<PagingData<FavoriteAndMovie>>

    suspend fun setMovieFavorite(movie: FavoriteAndMovie, state: Boolean)

    suspend fun getFavoriteMovieById(id: Int): Flow<FavoriteAndMovie>

}