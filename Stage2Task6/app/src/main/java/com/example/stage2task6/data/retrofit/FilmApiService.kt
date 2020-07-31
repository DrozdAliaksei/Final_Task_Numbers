package com.example.stage2task6.data.retrofit

import com.example.stage2task6.data.model.FilmJsonResponse
import com.example.stage2task6.data.model.FilmXmlResponse
import retrofit2.Response
import retrofit2.http.GET

interface FilmApiService {

    @GET("themes/rss/id")
    suspend fun getFilmsFromXml(): Response<FilmXmlResponse>

    @GET("artem-bagritsevich/rs.android.task.6/master/data/data.json")
    suspend fun getFilmsFromJson():Response<FilmJsonResponse>
}
