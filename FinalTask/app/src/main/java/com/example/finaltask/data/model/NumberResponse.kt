package com.example.finaltask.data.model

import com.example.finaltask.data.local.model.Number
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class NumberResponse(
    @Json(name = "text") val text: String,
    @Json(name = "found") val found: Boolean,
    @Json(name = "number") val number: Int,
    @Json(name = "type") val type: String,
    @Json(name = "date") val date: String?,
    @Json(name = "year") val year: String?
){
    fun toNumber(): Number{
        return Number(
            text = this.text,
            found = this.found,
            number = this.number,
            type = this.type,
            date = this.date,
            year = this.year
        )
    }
}
