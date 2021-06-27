package dev.adryanev.dicoding.moviejetpack.data.repositories

import androidx.paging.PagingData
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieList(): Flow<PagingData<MovieUi>>

    suspend fun getMovieById(id: Int): Flow<MovieUi>

}