package dev.adryanev.dicoding.moviejetpack.data.remote

import androidx.paging.PagingState
import dev.adryanev.dicoding.moviejetpack.data.constants.Constants
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.remote.api.Webservice
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.tvshows.ResponseListTv
import javax.inject.Inject

class TvShowRemoteDataSource @Inject constructor(private val movieDbWebservice: Webservice) : BaseDataSource<ResponseListTv>() {

    suspend fun getTvShowList(page: Int) = getResult {
        movieDbWebservice.getTvList(page)
    }

}