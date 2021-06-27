package dev.adryanev.dicoding.moviejetpack.data.remote.api

import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.movies.ResponseListMovie
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.tvshows.ResponseListTv
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Webservice {

    @GET("discover/movie")
    suspend fun getMovieList(@Query("page") page: Int): Response<ResponseListMovie>

    @GET("discover/tv")
    suspend fun getTvList(@Query("page") page: Int): Response<ResponseListTv>

    @GET("tv/{tv_id}")
    suspend fun getTvShow(@Path("tv_id") id: Int): Response<TvShow>

    @GET("movie/{movie_id}")
    suspend fun getMovie(@Path("movie_id") id: Int): Response<Movie>
}