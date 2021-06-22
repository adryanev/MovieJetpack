package dev.adryanev.dicoding.moviejetpack.di

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import androidx.paging.PagingConfig
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.adryanev.dicoding.moviejetpack.data.constants.Constants
import dev.adryanev.dicoding.moviejetpack.data.local.preference.AppPrefences
import dev.adryanev.dicoding.moviejetpack.data.local.preference.Preference
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun provideAppContext(@ApplicationContext context: Context): Context = context

    @Singleton
    @Provides
    fun provideResources(context: Context) : Resources = context.resources

    @Singleton
    @Provides
    fun provideAssetManager(context: Context): AssetManager = context.assets

    @Singleton
    @Provides
    fun providePreference(prefs: AppPrefences): Preference = prefs

    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()

    @Singleton
    @Provides
    fun providePagingConfig(): PagingConfig = PagingConfig(pageSize = Constants.PAGE_SIZE, prefetchDistance = 2)
}