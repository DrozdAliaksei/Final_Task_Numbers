package com.example.stage2task6.data

import com.example.stage2task6.data.local.model.Film
import com.example.stage2task6.data.model.FilmXmlResponse
import com.example.stage2task6.data.retrofit.FilmApiService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object DataSource {
    private val retrofit = Retrofit.Builder()
        .addConverterFactory(SimpleXmlConverterFactory.create())
//        .addConverterFactory(MoshiConverterFactory.create())
        .baseUrl("https://www.ted.com")
//        .baseUrl("https://raw.githubusercontent.com")
        .build()

    private val FilmService = retrofit.create(
        FilmApiService::class.java
    )

    suspend fun getFilmsFromXml(): Deferred<Response<FilmXmlResponse>> {
        return GlobalScope.async { FilmService.getFilmsFromXml() }
    }

    suspend fun getFilmsFromJson(): List<Film> {
        val response = FilmService.getFilmsFromJson()
        if (response.isSuccessful) {
            return response.body()
                ?.channel
                ?.items
                ?.map { item ->
                    Film(
                        item.title,
                        item.image.url,
                        item.duration.time,
                        item.media.mediaContent
                            .map { mediaData ->
                                mediaData.mediaUrl
                            },
                        item.description
                    )
                }!!
        } else
            return listOf()
    }
}
