package dev.adryanev.dicoding.moviejetpack.data.repositories

import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.tvshows.ResponseListTv
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    suspend fun getTvShowList() :Flow<Resource<ResponseListTv>>
}