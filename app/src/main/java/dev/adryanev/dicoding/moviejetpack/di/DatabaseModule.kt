package dev.adryanev.dicoding.moviejetpack.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.adryanev.dicoding.moviejetpack.data.constants.Constants
import dev.adryanev.dicoding.moviejetpack.data.local.AppDatabase
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSource
import dev.adryanev.dicoding.moviejetpack.data.local.LocalDataSourceImpl
import dev.adryanev.dicoding.moviejetpack.data.local.dao.FavoriteDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.MovieDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.MovieRemoteKeyDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMovieDatabase(applicationContext: Context): AppDatabase =
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            Constants.DATABASE_NAME
        ).build()


    @Singleton
    @Provides
    fun provideSharedPrefs(context: Context): SharedPreferences =
        context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideMovieDao(movieDatabase: AppDatabase): MovieDao = movieDatabase.movieDao()


    @Singleton
    @Provides
    fun provideMovieRemoteKeyDao(movieDatabase: AppDatabase): MovieRemoteKeyDao =
        movieDatabase.movieRemoteKeyDao()

    @Singleton
    @Provides
    fun provideFavoriteDao(movieDatabase: AppDatabase): FavoriteDao =
        movieDatabase.favoriteDao()

    @Singleton
    @Provides
    fun provideLocalDataSource(
        movieDao: MovieDao,
        movieRemoteKeyDao: MovieRemoteKeyDao,
        favoriteDao: FavoriteDao
    ): LocalDataSource = LocalDataSourceImpl(movieDao, movieRemoteKeyDao, favoriteDao)
}