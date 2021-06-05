package dev.adryanev.dicoding.moviejetpack.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.adryanev.dicoding.moviejetpack.BuildConfig
import dev.adryanev.dicoding.moviejetpack.data.remote.MovieDbWebservice
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
            .newBuilder()
            .addNetworkInterceptor(HttpLoggingInterceptor())
            .addInterceptor {
                val url = it.request().url
                val newUrl = url.newBuilder().addQueryParameter("api_key",BuildConfig.API_KEY).build()
                val newRequest = it.request().newBuilder().url(newUrl).build()
                it.proceed(newRequest)
            }
            .build()
    }

    @Provides
    fun provideTheMovieDbService(retrofit: Retrofit) : MovieDbWebservice = retrofit.create(MovieDbWebservice::class.java)

}

