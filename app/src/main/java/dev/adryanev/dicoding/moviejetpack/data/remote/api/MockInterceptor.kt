package dev.adryanev.dicoding.moviejetpack.data.remote.api

import android.content.res.AssetManager
import dev.adryanev.dicoding.moviejetpack.BuildConfig
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection

class MockInterceptor(
    private val assets: AssetManager
) : Interceptor {
    companion object {
        private const val MOCK_MOVIE_LIST = "mock_movie_list.json"
        private const val MOCK_TV_LIST = "mock_movie_list.json"
    }

    /**
     * add mock_response.json to mock
     * comment to use real api
     */
    @Throws(IllegalAccessError::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url.toUri().toString()
            val responseString = when {
                uri.contains("default") -> ""
                else -> null
            }

            return if (responseString.isNullOrBlank()) {
                chain.proceed(chain.request())
            } else {
                chain.proceed(chain.request())
                    .newBuilder()
                    .code(HttpURLConnection.HTTP_OK)
                    .protocol(Protocol.HTTP_2)
                    .message(responseString)
                    .body(
                        responseString.toByteArray()
                            .toResponseBody("application/json".toMediaTypeOrNull())
                    )
                    .addHeader("content-type", "application/json")
                    .build()
            }
        } else {
            //just to be on safe side.
            throw IllegalAccessError(
                "MockInterceptor is only meant for Testing Purposes and " +
                        "bound to be used only with DEBUG mode"
            )
        }
    }


}