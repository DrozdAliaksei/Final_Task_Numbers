package com.example.stage2task6.data

import com.example.stage2task6.data.model.FilmJsonResponse
import com.example.stage2task6.data.model.FilmXmlResponse
import com.example.stage2task6.data.retrofit.FilmApiService
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

object DataSource {
    private lateinit var retrofit: Retrofit
    private lateinit var FilmService: FilmApiService

    private fun buildRetrofitToWorkWithRepo() {
        retrofit = Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl("https://raw.githubusercontent.com")
            .build()
        FilmService = retrofit.create(FilmApiService::class.java)
    }

    private fun buildRetrofitToWorkWithRss() {
        retrofit = Retrofit.Builder()
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .baseUrl("https://www.ted.com")
            .build()
        FilmService = retrofit.create(FilmApiService::class.java)
    }

    suspend fun getFilmsFromXmlAsync(): Deferred<Response<FilmXmlResponse>> {
        buildRetrofitToWorkWithRss()
        return GlobalScope.async { FilmService.getFilmsFromXml() }
    }

    suspend fun getFilmsFromJsonAsync(): Deferred<Response<FilmJsonResponse>> {
        buildRetrofitToWorkWithRepo()
        return GlobalScope.async { FilmService.getFilmsFromJson() }
    }
}
