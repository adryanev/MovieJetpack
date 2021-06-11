package dev.adryanev.dicoding.moviejetpack.data.repositories

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies.ResponseListMovie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getMovieList() : Flow<Resource<ResponseListMovie>>
}