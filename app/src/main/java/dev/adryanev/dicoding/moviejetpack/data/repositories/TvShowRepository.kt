package dev.adryanev.dicoding.moviejetpack.data.repositories

import androidx.paging.PagingData
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import kotlinx.coroutines.flow.Flow

interface TvShowRepository {
    suspend fun getTvShowList(): Flow<PagingData<TvShow>>
    suspend fun getTvShowById(id: Int): Flow<TvShow>
}