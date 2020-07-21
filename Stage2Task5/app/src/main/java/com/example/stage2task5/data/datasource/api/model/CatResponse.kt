package com.example.stage2task5.data.datasource.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CatResponse(
    @Json(name = "id") val id: String?,
    @Json(name = "url") val imageUrl: String?
)
