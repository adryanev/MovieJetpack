package dev.adryanev.dicoding.moviejetpack.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.adryanev.dicoding.moviejetpack.data.entities.Favorite
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieRemoteKey
import dev.adryanev.dicoding.moviejetpack.data.entities.MovieUi
import dev.adryanev.dicoding.moviejetpack.data.local.converters.CountryConverters
import dev.adryanev.dicoding.moviejetpack.data.local.converters.DateConverter
import dev.adryanev.dicoding.moviejetpack.data.local.converters.GenreConverters
import dev.adryanev.dicoding.moviejetpack.data.local.dao.FavoriteDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.MovieDao
import dev.adryanev.dicoding.moviejetpack.data.local.dao.MovieRemoteKeyDao

@Database(
    entities = [MovieUi::class, MovieRemoteKey::class, Favorite::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(GenreConverters::class, CountryConverters::class, DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun movieRemoteKeyDao(): MovieRemoteKeyDao
    abstract fun favoriteDao(): FavoriteDao
}