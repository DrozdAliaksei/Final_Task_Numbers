package com.example.stage2task6.mvp.model

import com.example.stage2task6.data.DataSource
import com.example.stage2task6.data.model.FilmXmlResponse
import retrofit2.Response

class FilmModel {
    suspend fun getData(): Response<FilmXmlResponse> {
        return DataSource.getFilmsFromXml().await()
    }
}
