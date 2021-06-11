package dev.adryanev.dicoding.moviejetpack.data.repositories.impl

import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.local.dao.TvShowDao
import dev.adryanev.dicoding.moviejetpack.data.remote.TvShowRemoteDataSource
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.data.entities.Resource
import dev.adryanev.dicoding.moviejetpack.data.remote.responses.tvshows.ResponseListTv
import dev.adryanev.dicoding.moviejetpack.utils.networkBoundResource
import dev.adryanev.dicoding.moviejetpack.utils.networkOnlyResource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TvShowRepositoryImpl @Inject constructor(
    private val remoteDataSource: TvShowRemoteDataSource,
) : TvShowRepository {

//    override suspend fun getTvShowList(): Flow<Resource<List<TvShow>?>> = networkBoundResource(
//        query = { localDataSource.getAllTvShow() },
//        fetch = { remoteDataSource.getTvShowList() },
//        saveFetchResult = { items -> localDataSource.insert(items.data?.results) }
//    )

    override suspend fun getTvShowList(): Flow<Resource<ResponseListTv>> = networkOnlyResource(
        fetch = {remoteDataSource.getTvShowList()},
        processResult = {it.data!!}
    )

}