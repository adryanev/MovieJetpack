package dev.adryanev.dicoding.moviejetpack.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSourceImpl
import dev.adryanev.dicoding.moviejetpack.data.repositories.FavoriteRepository
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.data.repositories.impl.FavoriteRepositoryImpl
import dev.adryanev.dicoding.moviejetpack.data.repositories.impl.MovieRepositoryImpl
import dev.adryanev.dicoding.moviejetpack.data.repositories.impl.TvShowRepositoryImpl

@Module
@InstallIn(ActivityComponent::class)
abstract class DataModule {

    @Binds
    @ActivityScoped
    abstract fun bindLocalDataSource(localDataSourceImpl: LocalDataSourceImpl): LocalDataSource

    @Binds
    @ActivityScoped
    abstract fun bindMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository

    @Binds
    @ActivityScoped
    abstract fun bindTvShowRepository(tvShowRepositoryImpl: TvShowRepositoryImpl): TvShowRepository

    @Binds
    @ActivityScoped
    abstract fun bindFavoriteRepository(favoriteRepository: FavoriteRepositoryImpl): FavoriteRepository
}