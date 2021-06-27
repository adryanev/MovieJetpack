package dev.adryanev.dicoding.moviejetpack.di

import android.content.res.AssetManager
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.adryanev.dicoding.moviejetpack.BuildConfig
import dev.adryanev.dicoding.moviejetpack.data.remote.api.MockInterceptor
import dev.adryanev.dicoding.moviejetpack.data.remote.api.Webservice
import dev.adryanev.dicoding.moviejetpack.data.remote.sources.MovieRemoteDataSource
import dev.adryanev.dicoding.moviejetpack.data.remote.sources.TvShowRemoteDataSource
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideHeaderInterceptor(): Interceptor =
        Interceptor {
            val url = it.request().url
            val newUrl = url.newBuilder().addQueryParameter("api_key", BuildConfig.API_KEY).build()
            val newRequest = it.request().newBuilder().url(newUrl).build()
            it.proceed(newRequest)
        }

    @Singleton
    @Provides
    @Named("logging")
    fun provideLoggingInterceptor(): Interceptor =
        HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }


    @Singleton
    @Provides
    fun provideOkHttpClient(
        headInterceptor: Interceptor,
        @Named("mock") mockInterceptor: MockInterceptor,
        @Named("logging") httpInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addInterceptor(headInterceptor)
            .addNetworkInterceptor(StethoInterceptor())
            .addNetworkInterceptor(httpInterceptor)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(mockInterceptor)
                }

            }
            .build()
    }

    @Singleton
    @Provides
    fun provideTheMovieDbService(retrofit: Retrofit): Webservice =
        retrofit.create(Webservice::class.java)

    @Singleton
    @Provides
    @Named("mock")
    fun provideMockInterceptor(assetManager: AssetManager): MockInterceptor =
        MockInterceptor(assetManager)

    @Singleton
    @Provides
    fun provideMovieRemoteDataSource(movieDbWebservice: Webservice) =
        MovieRemoteDataSource(movieDbWebservice)

    @Singleton
    @Provides
    fun provideTvShowRemoteDataSource(movieDbWebservice: Webservice) =
        TvShowRemoteDataSource(movieDbWebservice)
}

