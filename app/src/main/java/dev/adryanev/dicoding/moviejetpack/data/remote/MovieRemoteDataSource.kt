package dev.adryanev.dicoding.moviejetpack.data.remote

import dev.adryanev.dicoding.moviejetpack.data.remote.api.Webservice
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies.ResponseListMovie
import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import timber.log.Timber
import javax.inject.Inject

class MovieRemoteDataSource @Inject constructor(private val movieDbWebservice: Webservice) : BaseDataSource() {

    suspend fun getMovieList(): Resource<ResponseListMovie> = getResult {
        movieDbWebservice.getMovieList()
    }
    suspend fun getTvShowList() = getResult {
        movieDbWebservice.getTvList()
    }
}