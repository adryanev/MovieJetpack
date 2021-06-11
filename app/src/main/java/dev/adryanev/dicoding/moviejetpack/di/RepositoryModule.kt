package dev.adryanev.dicoding.moviejetpack.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.adryanev.dicoding.moviejetpack.data.repositories.MovieRepository
import dev.adryanev.dicoding.moviejetpack.data.repositories.TvShowRepository
import dev.adryanev.dicoding.moviejetpack.data.repositories.impl.MovieRepositoryImpl
import dev.adryanev.dicoding.moviejetpack.data.repositories.impl.TvShowRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RepositoryModule {

    @Singleton
    @Provides
    fun provideMovieRepository(movieRepositoryImpl: MovieRepositoryImpl): MovieRepository = movieRepositoryImpl

    @Singleton
    @Provides
    fun provideTvShowRepository(tvShowRepository: TvShowRepositoryImpl): TvShowRepository = tvShowRepository
}