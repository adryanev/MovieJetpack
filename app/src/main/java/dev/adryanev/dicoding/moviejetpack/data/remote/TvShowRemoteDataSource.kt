package dev.adryanev.dicoding.moviejetpack.data.remote

import dev.adryanev.dicoding.moviejetpack.data.remote.api.Webservice
import javax.inject.Inject

class TvShowRemoteDataSource @Inject constructor(private val movieDbWebservice: Webservice) : BaseDataSource() {

    suspend fun getTvShowList() = getResult {
        movieDbWebservice.getTvList()
    }
}