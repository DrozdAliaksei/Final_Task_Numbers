package com.example.stage2task6.mvp.model

import com.example.stage2task6.data.DataSource
import com.example.stage2task6.data.local.model.Film

class FilmModel {
    suspend fun getData(): Result { //Response<FilmXmlResponse>
        val response = DataSource.getFilmsFromXml().await()
        return if (response.isSuccessful) {
            val items = response.body()
                ?.channel
                ?.itemsList?.map { item ->
                    Film(
                        item.title,
                        item.image?.url,
                        item.duration,
                        item.media?.content?.map { it.mediaUrl },
                        item.description
                    )
                }!!
            Result.ResultSuccess(items)
        } else
            Result.ResultFail
    }
}
