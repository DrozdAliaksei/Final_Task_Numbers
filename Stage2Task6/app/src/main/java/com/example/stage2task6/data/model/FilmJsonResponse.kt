package com.example.stage2task6.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class FilmJsonResponse(
    @Json(name = "channel") val channel: JsonChannel
)

@JsonClass(generateAdapter = true)
data class JsonChannel(
    @Json(name = "item") val items: List<Item>
)

@JsonClass(generateAdapter = true)
data class Item(
    @Json(name = "title") val title: String,
    @Json(name = "image") val image: Image,
    @Json(name = "description") val description: String,
    @Json(name = "duration") val duration: Duration,
    @Json(name = "group") val media: Media
)

@JsonClass(generateAdapter = true)
data class Image(
    @Json(name = "url") val url: String
)

@JsonClass(generateAdapter = true)
data class Duration(
    @Json(name = "text") val time: String
)

@JsonClass(generateAdapter = true)
data class Media(
    @Json(name = "content") val mediaContent: List<MediaData>
)

@JsonClass(generateAdapter = true)
data class MediaData(
    @Json(name = "url") val mediaUrl: String
)

