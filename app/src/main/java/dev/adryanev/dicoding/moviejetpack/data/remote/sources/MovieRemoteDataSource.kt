package dev.adryanev.dicoding.moviejetpack.data.remote.sources

import dev.adryanev.dicoding.moviejetpack.data.entities.DataResult
import dev.adryanev.dicoding.moviejetpack.data.remote.api.Webservice
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies.ResponseListMovie
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieDbWebservice: Webservice) :
    BaseDataSource<ResponseListMovie>() {

    suspend fun getMovieList(page: Int): DataResult<ResponseListMovie> = getResult {
        movieDbWebservice.getMovieList(page)
    }

}