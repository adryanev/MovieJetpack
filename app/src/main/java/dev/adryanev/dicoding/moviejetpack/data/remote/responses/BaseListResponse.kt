package dev.adryanev.dicoding.moviejetpack.data.remote.responses

import com.squareup.moshi.Json
import dev.adryanev.dicoding.moviejetpack.data.entities.Movie

open class BaseListResponse<ItemType>(
    @Json(name="page")
    val page: Int? = null,

    @Json(name="total_pages")
    val totalPages: Int? = null,

    @Json(name="results")
    var results: List<ItemType>? = null,

    @Json(name="total_results")
    val totalResults: Int? = null
): BaseResponse()