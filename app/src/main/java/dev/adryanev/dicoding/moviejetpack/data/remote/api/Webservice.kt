package dev.adryanev.dicoding.moviejetpack.data.remote.api

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies.ResponseListMovie
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.tvshows.ResponseListTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Webservice {

    @GET("trending/movie/week")
    suspend fun getMovieList(): Response<ResponseListMovie>

    @GET("trending/tv/week")
    suspend fun getTvList(): Response<ResponseListTv>

//    @GET("tv/{tv_id}")
//    suspend fun getTvShow(@Path("tv_id") id:Int) : Response<ResponseTvDetail>
//
//    @GET("movie/{movie_id}")
//    suspend fun getMovie(@Path("movie_id") id:Int) : Response<Movie>
}