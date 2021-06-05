package dev.adryanev.dicoding.moviejetpack.data

import dev.adryanev.dicoding.moviejetpack.data.remote.MovieRemoteDataSource
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: MovieRemoteDataSource
){
}