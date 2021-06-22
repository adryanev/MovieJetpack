package dev.adryanev.dicoding.moviejetpack.data.repositories

import androidx.paging.PagingData
import dev.adryanev.dicoding.moviejetpack.data.entities.DataResult
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.Result
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieList(): Flow<PagingData<Movie>>

    suspend fun getMovieById(id:Int): Flow<Movie>
}