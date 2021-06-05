package dev.adryanev.dicoding.moviejetpack.data.remote

import dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies.ResponseListMovie
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.tvshows.ResponseListTv
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDbWebservice {

    @GET("trending/movie/day")
    suspend fun getMovieList(): List<ResponseListMovie>

    @GET("trending/tv/day")
    suspend fun getTvList(): List<ResponseListTv>

    @GET("tv/{id}")
    suspend fun getTvShow(@Path("id") id:Int)
}