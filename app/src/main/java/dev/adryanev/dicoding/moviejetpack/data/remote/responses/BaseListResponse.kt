package dev.adryanev.dicoding.moviejetpack.data.remote.responses

import com.squareup.moshi.Json

open class BaseListResponse<ItemType>(
    @Json(name = "page")
    var page: Int? = null,

    @Json(name = "total_pages")
    var totalPages: Int? = null,

    @Json(name = "results")
    var results: List<ItemType>? = null,

    @Json(name = "total_results")
    var totalResults: Int? = null
) : BaseResponse()