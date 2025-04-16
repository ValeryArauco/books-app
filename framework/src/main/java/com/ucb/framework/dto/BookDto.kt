package com.ucb.framework.dto

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookDto(
    @Json(name = "title")
    val title: String,
    @Json(name = "author_name")
    val author: List<String>? = null,
    @Json(name = "first_publish_year")
    val year: Int? = null
)