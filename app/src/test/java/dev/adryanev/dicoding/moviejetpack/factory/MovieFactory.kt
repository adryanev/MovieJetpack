package dev.adryanev.dicoding.moviejetpack.factory

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies.ResponseListMovie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun createMovieListResponse(): Flow<Resource<List<Movie>?>> = flow {
    val response = ResponseListMovie()
    val movie1 = Movie(id = 1)
    val movie2 = Movie(id = 2)
    val movie3 = Movie(id = 3)
    val movie4 = Movie(id = 4)
    response.results = arrayListOf(movie1, movie2, movie3, movie4)

    emit(Resource.success(response.results))

}
