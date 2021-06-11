package dev.adryanev.dicoding.moviejetpack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.adryanev.dicoding.moviejetpack.data.local.converters.GenreConverters
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie
import dev.adryanev.dicoding.moviejetpack.data.entities.TvShow
import dev.adryanev.dicoding.moviejetpack.data.local.converters.CountryConverters
import dev.adryanev.dicoding.moviejetpack.data.local.dao.MovieDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.TvShowDao

@Database(entities = [Movie::class, TvShow::class], version = 1, exportSchema = false)
@TypeConverters(GenreConverters::class, CountryConverters::class)
abstract class AppDatabase: RoomDatabase(){
    abstract fun movieDao(): MovieDao
    abstract fun tvShowDao(): TvShowDao
}